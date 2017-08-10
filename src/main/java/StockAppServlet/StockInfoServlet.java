package StockAppServlet;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import StockApp.DataServer;
import StockAppUtils.JsonUtils;

@RestController
public class StockInfoServlet 
{
    @RequestMapping("/info")
    public String queryStockData(@RequestParam(value="query") String query) throws JsonProcessingException 
    {
        List<String> stockInfoList = null;
        if (query.equals("All"))
        {
        	stockInfoList = DataServer.instance().getAllStockId();
        	stockInfoList.addAll(DataServer.instance().getAllStockMarketId());
        }
        else if(query.equals("stock"))
        {
        	stockInfoList = DataServer.instance().getAllStockId();
        }
        else if(query.equals("market"))
        {
        	stockInfoList = DataServer.instance().getAllStockMarketId();
        }
        return JsonUtils.getJsonString(stockInfoList);
    }
    
    @RequestMapping("/add/stock")
    public String addStockId(@RequestParam(value="query") String query) 
    {
    	DataServer.instance().addStockId(query);
        return "success";
    }
    
    @RequestMapping("/add/market")
    public String addStockMarketId(@RequestParam(value="query") String query) 
    {
    	DataServer.instance().addStockMarketId(query);
        return "success";
    }
}