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
 * <p>StockMarketInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="StockMarketInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/StockXMLSchema}StockDataType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="curdot" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="curprice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="dealnumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="turnover" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StockMarketInfo", propOrder = {
    "name",
    "curdot",
    "curprice",
    "rate",
    "dealnumber",
    "turnover"
})
public class StockMarketInfo
    extends StockDataType
{

    @XmlElement(required = true)
    protected String name;
    protected float curdot;
    protected float curprice;
    protected float rate;
    protected int dealnumber;
    protected float turnover;

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
     * 获取curprice属性的值。
     * 
     */
    public float getCurprice() {
        return curprice;
    }

    /**
     * 设置curprice属性的值。
     * 
     */
    public void setCurprice(float value) {
        this.curprice = value;
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
     * 获取dealnumber属性的值。
     * 
     */
    public int getDealnumber() {
        return dealnumber;
    }

    /**
     * 设置dealnumber属性的值。
     * 
     */
    public void setDealnumber(int value) {
        this.dealnumber = value;
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

}
