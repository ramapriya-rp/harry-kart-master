package se.atg.harrykart.model;

import lombok.Data;

import java.util.List;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

import org.springframework.validation.annotation.Validated;

@Data
@Validated
@XmlRootElement(name = "harryKart")
@XmlAccessorType(XmlAccessType.FIELD)
public class HarryKartDO {

    @NotNull
    @Min(value = 0, message = "numberOfLoops field must be greater than or equal to 0")
    private Integer numberOfLoops;
    
    @XmlElementWrapper(name = "startList")
    @XmlElement(name = "participant")
    private List<ParticipantDO> startList;
    
    @XmlElementWrapper(name = "powerUps")
    @XmlElement(name = "loop")
    private List<LoopDO> powerUps;
    
}