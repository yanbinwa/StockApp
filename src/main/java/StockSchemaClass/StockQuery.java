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
 * <p>StockQuery complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="StockQuery">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/StockXMLSchema}StockDataType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startTimestamp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="endTimestamp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="interval" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StockQuery", propOrder = {
    "id",
    "startTimestamp",
    "endTimestamp",
    "interval",
    "type"
})
public class StockQuery
    extends StockDataType
{

    @XmlElement(required = true)
    protected String id;
    protected long startTimestamp;
    protected long endTimestamp;
    @XmlElement(required = true)
    protected String interval;
    @XmlElement(required = true)
    protected String type;

    /**
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
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

    /**
     * 获取interval属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterval() {
        return interval;
    }

    /**
     * 设置interval属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterval(String value) {
        this.interval = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
