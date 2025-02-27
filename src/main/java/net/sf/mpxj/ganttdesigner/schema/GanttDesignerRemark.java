//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.3
// See https://eclipse-ee4j.github.io/jaxb-ri
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2023.06.16 at 11:06:07 AM BST
//

package net.sf.mpxj.ganttdesigner.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * &lt;p&gt;Java class for ganttDesignerRemark complex type.
 *
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 *
 * &lt;pre&gt;
 * &amp;lt;complexType name="ganttDesignerRemark"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Task" maxOccurs="unbounded"&amp;gt;
 *           &amp;lt;complexType&amp;gt;
 *             &amp;lt;complexContent&amp;gt;
 *               &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *                 &amp;lt;attribute name="Row" type="{http://www.w3.org/2001/XMLSchema}int" /&amp;gt;
 *               &amp;lt;/restriction&amp;gt;
 *             &amp;lt;/complexContent&amp;gt;
 *           &amp;lt;/complexType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD) @XmlType(name = "ganttDesignerRemark", propOrder =
{
   "task"
}) public class GanttDesignerRemark
{

   @XmlElement(name = "Task", required = true) protected List<GanttDesignerRemark.Task> task;

   /**
    * Gets the value of the task property.
    *
    * &lt;p&gt;
    * This accessor method returns a reference to the live list,
    * not a snapshot. Therefore any modification you make to the
    * returned list will be present inside the JAXB object.
    * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the task property.
    *
    * &lt;p&gt;
    * For example, to add a new item, do as follows:
    * &lt;pre&gt;
    *    getTask().add(newItem);
    * &lt;/pre&gt;
    *
    *
    * &lt;p&gt;
    * Objects of the following type(s) are allowed in the list
    * {@link GanttDesignerRemark.Task }
    *
    *
    */
   public List<GanttDesignerRemark.Task> getTask()
   {
      if (task == null)
      {
         task = new ArrayList<>();
      }
      return this.task;
   }

   /**
    * &lt;p&gt;Java class for anonymous complex type.
    *
    * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
    *
    * &lt;pre&gt;
    * &amp;lt;complexType&amp;gt;
    *   &amp;lt;complexContent&amp;gt;
    *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
    *       &amp;lt;attribute name="Row" type="{http://www.w3.org/2001/XMLSchema}int" /&amp;gt;
    *     &amp;lt;/restriction&amp;gt;
    *   &amp;lt;/complexContent&amp;gt;
    * &amp;lt;/complexType&amp;gt;
    * &lt;/pre&gt;
    *
    *
    */
   @XmlAccessorType(XmlAccessType.FIELD) @XmlType(name = "", propOrder =
   {
      "content"
   }) public static class Task
   {

      @XmlValue protected String content;
      @XmlAttribute(name = "Row") protected Integer row;

      /**
       * Gets the value of the content property.
       *
       * @return
       *     possible object is
       *     {@link String }
       *
       */
      public String getContent()
      {
         return content;
      }

      /**
       * Sets the value of the content property.
       *
       * @param value
       *     allowed object is
       *     {@link String }
       *
       */
      public void setContent(String value)
      {
         this.content = value;
      }

      /**
       * Gets the value of the row property.
       *
       * @return
       *     possible object is
       *     {@link Integer }
       *
       */
      public Integer getRow()
      {
         return row;
      }

      /**
       * Sets the value of the row property.
       *
       * @param value
       *     allowed object is
       *     {@link Integer }
       *
       */
      public void setRow(Integer value)
      {
         this.row = value;
      }

   }

}
