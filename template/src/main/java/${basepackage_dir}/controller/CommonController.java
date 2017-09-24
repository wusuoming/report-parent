<#assign className=table.className>
<#assign classNameLower=className?uncap_first>
        package ${basepackage}.controller;
        import ${basepackage}.service.CommonService;
        import com.appc.litsso.common.util.PageDto;
        import com.appc.litsso.common.util.PageUtils;
        import io.swagger.annotations.ApiOperation;
        import io.swagger.annotations.ApiParam;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.propertyeditors.CustomDateEditor;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.domain.Sort;
        import org.springframework.util.StringUtils;
        import org.springframework.web.bind.WebDataBinder;
        import org.springframework.web.bind.annotation.*;

        import java.io.Serializable;
        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.List;

/**
 * UserController
 *
 * @author : panda
 * @version : Ver 1.0
 * @date : 2017-6-28
 */
public class CommonController<T, BaseService extends CommonService<T>> {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    protected BaseService baseService;


    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public int insert(@RequestBody T t) {
        return baseService.insert(t);
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    public int updateById(@RequestBody T t) {
        return baseService.updateById(t);
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    public int deleteById(@ApiParam("主键") @RequestParam(required = true) Serializable pk) {
        return baseService.deleteById(pk);
    }

    @ApiOperation(value = "根据ID获取", notes = "根据ID获取")
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public T getById(@ApiParam("主键") @RequestParam(required = true) Serializable pk) {
        return baseService.getById(pk);
    }

    @ApiOperation(value = "根据参数获取对象", notes = "根据参数获取对象")
    @RequestMapping(value = "/getEntity", method = RequestMethod.POST)
    public T getEntity(@RequestBody T t) {
        return baseService.getEntity(t);
    }


    @ApiOperation(value = "根据主键和字段获取相应的值", notes = "根据主键和字段获取相应的值")
    @RequestMapping(value = "/getValueById", method = RequestMethod.GET)
    public <V> V getValueById(@ApiParam("主键") @RequestParam(required = true) Object pk, @ApiParam("字段") @RequestParam(required = true) String fieldName) {
        return baseService.getValueById(pk, fieldName);
    }

    @ApiOperation(value = "批量新增", notes = "批量新增")
    @RequestMapping(value = "/insertBatch", method = RequestMethod.POST)
    public int insertBatch(@RequestBody List<T> list) {
        return baseService.insertBatch(list);
    }


    @ApiOperation(value = "根据参数获取对象", notes = "根据参数获取对象")
    @RequestMapping(value = "/getEntityList", method = RequestMethod.POST)
    public List<T> getEntityList(@RequestBody T t) {
        return baseService.getEntityList(t);
    }


    @ApiOperation(value = "根据参数分页获取对象", notes = "根据参数分页获取对象")
    @RequestMapping(value = "/getEntityListForPage", method = RequestMethod.POST)
    public PageDto<T> getEntityListForPage(@RequestBody T t,
                                           @ApiParam("第几页") @RequestParam(value = "page", defaultValue = "1") int page,
                                           @ApiParam("每页几条数据") @RequestParam(value = "rows", defaultValue = "10") int rows,
                                           @ApiParam("正序还是反序") @RequestParam(required = false) String sort,
                                           @ApiParam("排序字段") @RequestParam(required = false) String order) {
        Pageable pageable = null;
        if (!StringUtils.isEmpty(order) && !StringUtils.isEmpty(sort)) {
            Sort sortObj = new Sort(Sort.Direction.fromStringOrNull(order), sort);
            pageable = new PageRequest(page - 1, rows, sortObj);
        } else {
            pageable = new PageRequest(page - 1, rows);
        }
        return PageUtils.loadPage(baseService.getEntityListForPage(t, pageable));
    }


}