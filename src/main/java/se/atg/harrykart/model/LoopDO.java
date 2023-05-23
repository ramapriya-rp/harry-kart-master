package se.atg.harrykart.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;


@Data
@XmlRootElement(name = "loop")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoopDO {

	@XmlAttribute
	@Valid
	@Min(1)
    private Integer number;

	
	@XmlElement(name = "lane")
    @Valid
    @Size(min = 4)
    private List <LaneDO> lane;

}
