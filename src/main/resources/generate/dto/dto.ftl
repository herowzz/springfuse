package ${packageName};

<#list dtoImportSet as importName>
import ${importName};
</#list>

public class ${entityName}Dto {

	<#list fieldList as field>
	<#if field.timeType??>
	@JsonFormat(pattern = "${field.getTimeFormat()}")
	</#if>
	public ${field.classType} ${field.name};
	
	</#list>
	
	public static ${entityName}Dto copy(${entityName} ${entitySimpleName}) {
		${entityName}Dto dto = new ${entityName}Dto();
		<#list fieldList as field>
		<#if field.manyToOne>
		dto.${field.name} = ${entitySimpleName}.get${field.manyToOneName}().getId();
		</#if>
		<#if !field.manyToOne>
		dto.${field.name} = ${entitySimpleName}.get${field.getNameCapital()}();
		</#if>
		</#list>
		return dto;
	}
}