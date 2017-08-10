//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.03.11 时间 09:18:44 AM CST 
//


package StockSchemaClass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>StockHandicapData complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="StockHandicapData">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/StockXMLSchema}StockDataType">
 *       &lt;sequence>
 *         &lt;element name="outSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="inSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="exchangeRatio" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="priceEaringRatio" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="priceToBookRatio" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="swing" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="circulatedMarket" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalMarket" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="mainFlowIn" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="mainFlowOut" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="netMainFlowIn" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="netMainFlowInRatio" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="retailFlowIn" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="retailFlowOut" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="netRetailFlowIn" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="netRetailFlowInRatio" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StockHandicapData", propOrder = {
    "outSize",
    "inSize",
    "exchangeRatio",
    "priceEaringRatio",
    "priceToBookRatio",
    "swing",
    "circulatedMarket",
    "totalMarket",
    "mainFlowIn",
    "mainFlowOut",
    "netMainFlowIn",
    "netMainFlowInRatio",
    "retailFlowIn",
    "retailFlowOut",
    "netRetailFlowIn",
    "netRetailFlowInRatio"
})
public class StockHandicapData
    extends StockDataType
{

    protected int outSize;
    protected int inSize;
    protected float exchangeRatio;
    protected float priceEaringRatio;
    protected float priceToBookRatio;
    protected float swing;
    protected float circulatedMarket;
    protected float totalMarket;
    protected float mainFlowIn;
    protected float mainFlowOut;
    protected float netMainFlowIn;
    protected float netMainFlowInRatio;
    protected float retailFlowIn;
    protected float retailFlowOut;
    protected float netRetailFlowIn;
    protected float netRetailFlowInRatio;

    /**
     * 获取outSize属性的值。
     * 
     */
    public int getOutSize() {
        return outSize;
    }

    /**
     * 设置outSize属性的值。
     * 
     */
    public void setOutSize(int value) {
        this.outSize = value;
    }

    /**
     * 获取inSize属性的值。
     * 
     */
    public int getInSize() {
        return inSize;
    }

    /**
     * 设置inSize属性的值。
     * 
     */
    public void setInSize(int value) {
        this.inSize = value;
    }

    /**
     * 获取exchangeRatio属性的值。
     * 
     */
    public float getExchangeRatio() {
        return exchangeRatio;
    }

    /**
     * 设置exchangeRatio属性的值。
     * 
     */
    public void setExchangeRatio(float value) {
        this.exchangeRatio = value;
    }

    /**
     * 获取priceEaringRatio属性的值。
     * 
     */
    public float getPriceEaringRatio() {
        return priceEaringRatio;
    }

    /**
     * 设置priceEaringRatio属性的值。
     * 
     */
    public void setPriceEaringRatio(float value) {
        this.priceEaringRatio = value;
    }

    /**
     * 获取priceToBookRatio属性的值。
     * 
     */
    public float getPriceToBookRatio() {
        return priceToBookRatio;
    }

    /**
     * 设置priceToBookRatio属性的值。
     * 
     */
    public void setPriceToBookRatio(float value) {
        this.priceToBookRatio = value;
    }

    /**
     * 获取swing属性的值。
     * 
     */
    public float getSwing() {
        return swing;
    }

    /**
     * 设置swing属性的值。
     * 
     */
    public void setSwing(float value) {
        this.swing = value;
    }

    /**
     * 获取circulatedMarket属性的值。
     * 
     */
    public float getCirculatedMarket() {
        return circulatedMarket;
    }

    /**
     * 设置circulatedMarket属性的值。
     * 
     */
    public void setCirculatedMarket(float value) {
        this.circulatedMarket = value;
    }

    /**
     * 获取totalMarket属性的值。
     * 
     */
    public float getTotalMarket() {
        return totalMarket;
    }

    /**
     * 设置totalMarket属性的值。
     * 
     */
    public void setTotalMarket(float value) {
        this.totalMarket = value;
    }

    /**
     * 获取mainFlowIn属性的值。
     * 
     */
    public float getMainFlowIn() {
        return mainFlowIn;
    }

    /**
     * 设置mainFlowIn属性的值。
     * 
     */
    public void setMainFlowIn(float value) {
        this.mainFlowIn = value;
    }

    /**
     * 获取mainFlowOut属性的值。
     * 
     */
    public float getMainFlowOut() {
        return mainFlowOut;
    }

    /**
     * 设置mainFlowOut属性的值。
     * 
     */
    public void setMainFlowOut(float value) {
        this.mainFlowOut = value;
    }

    /**
     * 获取netMainFlowIn属性的值。
     * 
     */
    public float getNetMainFlowIn() {
        return netMainFlowIn;
    }

    /**
     * 设置netMainFlowIn属性的值。
     * 
     */
    public void setNetMainFlowIn(float value) {
        this.netMainFlowIn = value;
    }

    /**
     * 获取netMainFlowInRatio属性的值。
     * 
     */
    public float getNetMainFlowInRatio() {
        return netMainFlowInRatio;
    }

    /**
     * 设置netMainFlowInRatio属性的值。
     * 
     */
    public void setNetMainFlowInRatio(float value) {
        this.netMainFlowInRatio = value;
    }

    /**
     * 获取retailFlowIn属性的值。
     * 
     */
    public float getRetailFlowIn() {
        return retailFlowIn;
    }

    /**
     * 设置retailFlowIn属性的值。
     * 
     */
    public void setRetailFlowIn(float value) {
        this.retailFlowIn = value;
    }

    /**
     * 获取retailFlowOut属性的值。
     * 
     */
    public float getRetailFlowOut() {
        return retailFlowOut;
    }

    /**
     * 设置retailFlowOut属性的值。
     * 
     */
    public void setRetailFlowOut(float value) {
        this.retailFlowOut = value;
    }

    /**
     * 获取netRetailFlowIn属性的值。
     * 
     */
    public float getNetRetailFlowIn() {
        return netRetailFlowIn;
    }

    /**
     * 设置netRetailFlowIn属性的值。
     * 
     */
    public void setNetRetailFlowIn(float value) {
        this.netRetailFlowIn = value;
    }

    /**
     * 获取netRetailFlowInRatio属性的值。
     * 
     */
    public float getNetRetailFlowInRatio() {
        return netRetailFlowInRatio;
    }

    /**
     * 设置netRetailFlowInRatio属性的值。
     * 
     */
    public void setNetRetailFlowInRatio(float value) {
        this.netRetailFlowInRatio = value;
    }

}
