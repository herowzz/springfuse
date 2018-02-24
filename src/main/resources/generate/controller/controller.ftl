package ${packageName};

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.herowzz.springfuse.api.dto.ApiResult;
import com.github.herowzz.springfuse.api.dto.param.IdParam;
import com.github.herowzz.springfuse.api.dto.param.PageParam;
import com.github.herowzz.springfuse.core.bean.page.PageCommon;
import ${entityPackageName};
import ${dtoPackage}.${entityName}Dto;
import ${autowiredPackege}.${entityName}Service;

@RestController
@RequestMapping(value = "/${entitySimpleName}")
public class ${entityName}Controller {

	@Autowired
	private ${entityName}Service ${entitySimpleName}Service;

	@PostMapping(value = "/list")
	public ApiResult list(@RequestBody(required = false) PageParam pageParam) {
		Page<${entityName}> ${entitySimpleName}Page = ${entitySimpleName}Service.findPage(PageCommon.getPage(pageParam));
		Page<${entityName}Dto> ${entitySimpleName}DtoPage = ${entitySimpleName}Page.map(e -> ${entityName}Dto.copy(e));
		return ApiResult.build(${entitySimpleName}DtoPage);
	}

	@PostMapping(value = "/add")
	public ApiResult add(@RequestBody @Valid Add${entityName}Param param) {
		${entityName} ${entitySimpleName} = param.copy();
		${entitySimpleName}Service.save(${entitySimpleName});
		return ApiResult.ok();
	}

	@PostMapping(value = "/{id}")
	public ApiResult show(@PathVariable("id") ${entityName} ${entitySimpleName}) {
		if (${entitySimpleName} == null) {
			return ApiResult.objectNotFound();
		}
		return ApiResult.build(${entityName}Dto.copy(${entitySimpleName}));
	}

	@PostMapping(value = "/update")
	public ApiResult update(@RequestBody @Valid Update${entityName}Param param) {
		Optional<${entityName}> ${entitySimpleName}Optional = ${entitySimpleName}Service.findById(param.id);
		if (!${entitySimpleName}Optional.isPresent()) {
			return ApiResult.objectNotFound();
		}
		${entitySimpleName}Service.save(param.copy(${entitySimpleName}Optional.get()));
		return ApiResult.ok();
	}

	@PostMapping(value = "/delete")
	public ApiResult delete(@RequestBody @Valid IdParam param) {
		${entitySimpleName}Service.delete(param.id);
		return ApiResult.ok();
	}

}
