//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.03.11 时间 09:18:44 AM CST 
//


package StockSchemaClass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>StockData complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="StockData">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/StockXMLSchema}StockDataType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="openningPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="closingPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="currentPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="hprice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="lprice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="competitivePrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="auctionPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="turnover" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="increase" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="buyOne" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="buyOnePrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="buyTwo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="buyTwoPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="buyThree" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="buyThreePrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="buyFour" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="buyFourPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="buyFive" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="buyFivePrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="sellOne" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sellOnePrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="sellTwo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sellTwoPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="sellThree" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sellThreePrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="sellFour" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sellFourPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="sellFive" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sellFivePrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="minurl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dayurl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="weekurl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="monthurl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StockData", propOrder = {
    "name",
    "code",
    "date",
    "time",
    "openningPrice",
    "closingPrice",
    "currentPrice",
    "hprice",
    "lprice",
    "competitivePrice",
    "auctionPrice",
    "totalNumber",
    "turnover",
    "increase",
    "buyOne",
    "buyOnePrice",
    "buyTwo",
    "buyTwoPrice",
    "buyThree",
    "buyThreePrice",
    "buyFour",
    "buyFourPrice",
    "buyFive",
    "buyFivePrice",
    "sellOne",
    "sellOnePrice",
    "sellTwo",
    "sellTwoPrice",
    "sellThree",
    "sellThreePrice",
    "sellFour",
    "sellFourPrice",
    "sellFive",
    "sellFivePrice",
    "minurl",
    "dayurl",
    "weekurl",
    "monthurl"
})
public class StockData
    extends StockDataType
{

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String code;
    @XmlElement(required = true)
    protected String date;
    @XmlElement(required = true)
    protected String time;
    protected float openningPrice;
    protected float closingPrice;
    protected float currentPrice;
    protected float hprice;
    protected float lprice;
    protected float competitivePrice;
    protected float auctionPrice;
    protected int totalNumber;
    protected float turnover;
    protected float increase;
    protected int buyOne;
    protected float buyOnePrice;
    protected int buyTwo;
    protected float buyTwoPrice;
    protected int buyThree;
    protected float buyThreePrice;
    protected int buyFour;
    protected float buyFourPrice;
    protected int buyFive;
    protected float buyFivePrice;
    protected int sellOne;
    protected float sellOnePrice;
    protected int sellTwo;
    protected float sellTwoPrice;
    protected int sellThree;
    protected float sellThreePrice;
    protected int sellFour;
    protected float sellFourPrice;
    protected int sellFive;
    protected float sellFivePrice;
    @XmlElement(required = true)
    protected String minurl;
    @XmlElement(required = true)
    protected String dayurl;
    @XmlElement(required = true)
    protected String weekurl;
    @XmlElement(required = true)
    protected String monthurl;

    /**
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取code属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置code属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * 获取date属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * 设置date属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * 获取time属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置time属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTime(String value) {
        this.time = value;
    }

    /**
     * 获取openningPrice属性的值。
     * 
     */
    public float getOpenningPrice() {
        return openningPrice;
    }

    /**
     * 设置openningPrice属性的值。
     * 
     */
    public void setOpenningPrice(float value) {
        this.openningPrice = value;
    }

    /**
     * 获取closingPrice属性的值。
     * 
     */
    public float getClosingPrice() {
        return closingPrice;
    }

    /**
     * 设置closingPrice属性的值。
     * 
     */
    public void setClosingPrice(float value) {
        this.closingPrice = value;
    }

    /**
     * 获取currentPrice属性的值。
     * 
     */
    public float getCurrentPrice() {
        return currentPrice;
    }

    /**
     * 设置currentPrice属性的值。
     * 
     */
    public void setCurrentPrice(float value) {
        this.currentPrice = value;
    }

    /**
     * 获取hprice属性的值。
     * 
     */
    public float getHprice() {
        return hprice;
    }

    /**
     * 设置hprice属性的值。
     * 
     */
    public void setHprice(float value) {
        this.hprice = value;
    }

    /**
     * 获取lprice属性的值。
     * 
     */
    public float getLprice() {
        return lprice;
    }

    /**
     * 设置lprice属性的值。
     * 
     */
    public void setLprice(float value) {
        this.lprice = value;
    }

    /**
     * 获取competitivePrice属性的值。
     * 
     */
    public float getCompetitivePrice() {
        return competitivePrice;
    }

    /**
     * 设置competitivePrice属性的值。
     * 
     */
    public void setCompetitivePrice(float value) {
        this.competitivePrice = value;
    }

    /**
     * 获取auctionPrice属性的值。
     * 
     */
    public float getAuctionPrice() {
        return auctionPrice;
    }

    /**
     * 设置auctionPrice属性的值。
     * 
     */
    public void setAuctionPrice(float value) {
        this.auctionPrice = value;
    }

    /**
     * 获取totalNumber属性的值。
     * 
     */
    public int getTotalNumber() {
        return totalNumber;
    }

    /**
     * 设置totalNumber属性的值。
     * 
     */
    public void setTotalNumber(int value) {
        this.totalNumber = value;
    }

    /**
     * 获取turnover属性的值。
     * 
     */
    public float getTurnover() {
        return turnover;
    }

    /**
     * 设置turnover属性的值。
     * 
     */
    public void setTurnover(float value) {
        this.turnover = value;
    }

    /**
     * 获取increase属性的值。
     * 
     */
    public float getIncrease() {
        return increase;
    }

    /**
     * 设置increase属性的值。
     * 
     */
    public void setIncrease(float value) {
        this.increase = value;
    }

    /**
     * 获取buyOne属性的值。
     * 
     */
    public int getBuyOne() {
        return buyOne;
    }

    /**
     * 设置buyOne属性的值。
     * 
     */
    public void setBuyOne(int value) {
        this.buyOne = value;
    }

    /**
     * 获取buyOnePrice属性的值。
     * 
     */
    public float getBuyOnePrice() {
        return buyOnePrice;
    }

    /**
     * 设置buyOnePrice属性的值。
     * 
     */
    public void setBuyOnePrice(float value) {
        this.buyOnePrice = value;
    }

    /**
     * 获取buyTwo属性的值。
     * 
     */
    public int getBuyTwo() {
        return buyTwo;
    }

    /**
     * 设置buyTwo属性的值。
     * 
     */
    public void setBuyTwo(int value) {
        this.buyTwo = value;
    }

    /**
     * 获取buyTwoPrice属性的值。
     * 
     */
    public float getBuyTwoPrice() {
        return buyTwoPrice;
    }

    /**
     * 设置buyTwoPrice属性的值。
     * 
     */
    public void setBuyTwoPrice(float value) {
        this.buyTwoPrice = value;
    }

    /**
     * 获取buyThree属性的值。
     * 
     */
    public int getBuyThree() {
        return buyThree;
    }

    /**
     * 设置buyThree属性的值。
     * 
     */
    public void setBuyThree(int value) {
        this.buyThree = value;
    }

    /**
     * 获取buyThreePrice属性的值。
     * 
     */
    public float getBuyThreePrice() {
        return buyThreePrice;
    }

    /**
     * 设置buyThreePrice属性的值。
     * 
     */
    public void setBuyThreePrice(float value) {
        this.buyThreePrice = value;
    }

    /**
     * 获取buyFour属性的值。
     * 
     */
    public int getBuyFour() {
        return buyFour;
    }

    /**
     * 设置buyFour属性的值。
     * 
     */
    public void setBuyFour(int value) {
        this.buyFour = value;
    }

    /**
     * 获取buyFourPrice属性的值。
     * 
     */
    public float getBuyFourPrice() {
        return buyFourPrice;
    }

    /**
     * 设置buyFourPrice属性的值。
     * 
     */
    public void setBuyFourPrice(float value) {
        this.buyFourPrice = value;
    }

    /**
     * 获取buyFive属性的值。
     * 
     */
    public int getBuyFive() {
        return buyFive;
    }

    /**
     * 设置buyFive属性的值。
     * 
     */
    public void setBuyFive(int value) {
        this.buyFive = value;
    }

    /**
     * 获取buyFivePrice属性的值。
     * 
     */
    public float getBuyFivePrice() {
        return buyFivePrice;
    }

    /**
     * 设置buyFivePrice属性的值。
     * 
     */
    public void setBuyFivePrice(float value) {
        this.buyFivePrice = value;
    }

    /**
     * 获取sellOne属性的值。
     * 
     */
    public int getSellOne() {
        return sellOne;
    }

    /**
     * 设置sellOne属性的值。
     * 
     */
    public void setSellOne(int value) {
        this.sellOne = value;
    }

    /**
     * 获取sellOnePrice属性的值。
     * 
     */
    public float getSellOnePrice() {
        return sellOnePrice;
    }

    /**
     * 设置sellOnePrice属性的值。
     * 
     */
    public void setSellOnePrice(float value) {
        this.sellOnePrice = value;
    }

    /**
     * 获取sellTwo属性的值。
     * 
     */
    public int getSellTwo() {
        return sellTwo;
    }

    /**
     * 设置sellTwo属性的值。
     * 
     */
    public void setSellTwo(int value) {
        this.sellTwo = value;
    }

    /**
     * 获取sellTwoPrice属性的值。
     * 
     */
    public float getSellTwoPrice() {
        return sellTwoPrice;
    }

    /**
     * 设置sellTwoPrice属性的值。
     * 
     */
    public void setSellTwoPrice(float value) {
        this.sellTwoPrice = value;
    }

    /**
     * 获取sellThree属性的值。
     * 
     */
    public int getSellThree() {
        return sellThree;
    }

    /**
     * 设置sellThree属性的值。
     * 
     */
    public void setSellThree(int value) {
        this.sellThree = value;
    }

    /**
     * 获取sellThreePrice属性的值。
     * 
     */
    public float getSellThreePrice() {
        return sellThreePrice;
    }

    /**
     * 设置sellThreePrice属性的值。
     * 
     */
    public void setSellThreePrice(float value) {
        this.sellThreePrice = value;
    }

    /**
     * 获取sellFour属性的值。
     * 
     */
    public int getSellFour() {
        return sellFour;
    }

    /**
     * 设置sellFour属性的值。
     * 
     */
    public void setSellFour(int value) {
        this.sellFour = value;
    }

    /**
     * 获取sellFourPrice属性的值。
     * 
     */
    public float getSellFourPrice() {
        return sellFourPrice;
    }

    /**
     * 设置sellFourPrice属性的值。
     * 
     */
    public void setSellFourPrice(float value) {
        this.sellFourPrice = value;
    }

    /**
     * 获取sellFive属性的值。
     * 
     */
    public int getSellFive() {
        return sellFive;
    }

    /**
     * 设置sellFive属性的值。
     * 
     */
    public void setSellFive(int value) {
        this.sellFive = value;
    }

    /**
     * 获取sellFivePrice属性的值。
     * 
     */
    public float getSellFivePrice() {
        return sellFivePrice;
    }

    /**
     * 设置sellFivePrice属性的值。
     * 
     */
    public void setSellFivePrice(float value) {
        this.sellFivePrice = value;
    }

    /**
     * 获取minurl属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinurl() {
        return minurl;
    }

    /**
     * 设置minurl属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinurl(String value) {
        this.minurl = value;
    }

    /**
     * 获取dayurl属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDayurl() {
        return dayurl;
    }

    /**
     * 设置dayurl属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDayurl(String value) {
        this.dayurl = value;
    }

    /**
     * 获取weekurl属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeekurl() {
        return weekurl;
    }

    /**
     * 设置weekurl属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeekurl(String value) {
        this.weekurl = value;
    }

    /**
     * 获取monthurl属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonthurl() {
        return monthurl;
    }

    /**
     * 设置monthurl属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonthurl(String value) {
        this.monthurl = value;
    }

}
