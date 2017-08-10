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
 * <p>StockQueryResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="StockQueryResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/StockXMLSchema}StockDataType">
 *       &lt;sequence>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="startPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="endPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="hprice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="lprice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mainFlowIn" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="mainFlowOut" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="retailFlowIn" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="retailFlowOut" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="outSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="inSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StockQueryResult", propOrder = {
    "timestamp",
    "startPrice",
    "endPrice",
    "hprice",
    "lprice",
    "totalNumber",
    "mainFlowIn",
    "mainFlowOut",
    "retailFlowIn",
    "retailFlowOut",
    "outSize",
    "inSize"
})
public class StockQueryResult
    extends StockDataType
{

    protected long timestamp;
    protected float startPrice;
    protected float endPrice;
    protected float hprice;
    protected float lprice;
    protected int totalNumber;
    protected float mainFlowIn;
    protected float mainFlowOut;
    protected float retailFlowIn;
    protected float retailFlowOut;
    protected int outSize;
    protected int inSize;

    /**
     * 获取timestamp属性的值。
     * 
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * 设置timestamp属性的值。
     * 
     */
    public void setTimestamp(long value) {
        this.timestamp = value;
    }

    /**
     * 获取startPrice属性的值。
     * 
     */
    public float getStartPrice() {
        return startPrice;
    }

    /**
     * 设置startPrice属性的值。
     * 
     */
    public void setStartPrice(float value) {
        this.startPrice = value;
    }

    /**
     * 获取endPrice属性的值。
     * 
     */
    public float getEndPrice() {
        return endPrice;
    }

    /**
     * 设置endPrice属性的值。
     * 
     */
    public void setEndPrice(float value) {
        this.endPrice = value;
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

}
