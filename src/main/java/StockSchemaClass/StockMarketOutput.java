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
 * <p>StockMarketOutput complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="StockMarketOutput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/StockXMLSchema}StockDataType">
 *       &lt;sequence>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="curdot" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="startDot" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="endDot" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="dealNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="turnover" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="hdot" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ldot" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="swing" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="startTimestamp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="endTimestamp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StockMarketOutput", propOrder = {
    "timestamp",
    "date",
    "time",
    "curdot",
    "rate",
    "startDot",
    "endDot",
    "dealNumber",
    "turnover",
    "hdot",
    "ldot",
    "swing",
    "startTimestamp",
    "endTimestamp"
})
public class StockMarketOutput
    extends StockDataType
{

    protected long timestamp;
    @XmlElement(required = true)
    protected String date;
    @XmlElement(required = true)
    protected String time;
    protected float curdot;
    protected float rate;
    protected float startDot;
    protected float endDot;
    protected int dealNumber;
    protected float turnover;
    protected float hdot;
    protected float ldot;
    protected float swing;
    protected long startTimestamp;
    protected long endTimestamp;

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
     * 获取curdot属性的值。
     * 
     */
    public float getCurdot() {
        return curdot;
    }

    /**
     * 设置curdot属性的值。
     * 
     */
    public void setCurdot(float value) {
        this.curdot = value;
    }

    /**
     * 获取rate属性的值。
     * 
     */
    public float getRate() {
        return rate;
    }

    /**
     * 设置rate属性的值。
     * 
     */
    public void setRate(float value) {
        this.rate = value;
    }

    /**
     * 获取startDot属性的值。
     * 
     */
    public float getStartDot() {
        return startDot;
    }

    /**
     * 设置startDot属性的值。
     * 
     */
    public void setStartDot(float value) {
        this.startDot = value;
    }

    /**
     * 获取endDot属性的值。
     * 
     */
    public float getEndDot() {
        return endDot;
    }

    /**
     * 设置endDot属性的值。
     * 
     */
    public void setEndDot(float value) {
        this.endDot = value;
    }

    /**
     * 获取dealNumber属性的值。
     * 
     */
    public int getDealNumber() {
        return dealNumber;
    }

    /**
     * 设置dealNumber属性的值。
     * 
     */
    public void setDealNumber(int value) {
        this.dealNumber = value;
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
     * 获取hdot属性的值。
     * 
     */
    public float getHdot() {
        return hdot;
    }

    /**
     * 设置hdot属性的值。
     * 
     */
    public void setHdot(float value) {
        this.hdot = value;
    }

    /**
     * 获取ldot属性的值。
     * 
     */
    public float getLdot() {
        return ldot;
    }

    /**
     * 设置ldot属性的值。
     * 
     */
    public void setLdot(float value) {
        this.ldot = value;
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
     * 获取startTimestamp属性的值。
     * 
     */
    public long getStartTimestamp() {
        return startTimestamp;
    }

    /**
     * 设置startTimestamp属性的值。
     * 
     */
    public void setStartTimestamp(long value) {
        this.startTimestamp = value;
    }

    /**
     * 获取endTimestamp属性的值。
     * 
     */
    public long getEndTimestamp() {
        return endTimestamp;
    }

    /**
     * 设置endTimestamp属性的值。
     * 
     */
    public void setEndTimestamp(long value) {
        this.endTimestamp = value;
    }

}
