package se.atg.harrykart.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;


@Data
@XmlRootElement(name = "lane")
@XmlAccessorType(XmlAccessType.FIELD)
public class LaneDO {

	@XmlAttribute
    @Valid
    private Integer number;

   @XmlValue
    @NotNull
    private Integer value;
}
