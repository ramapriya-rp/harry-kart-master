
package se.atg.harrykart.model;


import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "participant")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParticipantDO {

	@Valid
	@NotNull
    private Integer lane;

	@Valid
    @NotNull
    @Size(min = 1, max = 100)
    private String name;

	@Valid
    @NotNull
    private Integer baseSpeed;
   

}
