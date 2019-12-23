var table;
var productSubCategoryIdList = [];
var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#productsubcategorylist');
        var oTable = table.dataTable({
            buttons: [
                {extend: '', className: 'dt-button btn red btn-outline addItem', text: '新增产品子类'},
                {extend: '', className: 'btn green btn-outline btnSyn', text: '批量同步'},
                {
                    extend: 'excel',
                    text: '导出excel',
                    title: '产品子类列表',
                    className: 'btn blue btn-outline',
                    exportOptions: {
                        modifier: {
                            page: 'current'
                        },
                        columns: '2,3,4,5,6,7,8,9,10'
                    }
                }
            ],
            "fnServerData": function (sSource, aoData, fnCallback, oSettings) {
                oSettings.jqXHR = $.ajax({
                    "dataType": 'json',
                    "type": "POST",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback,
                    "error": function () {
                        $("#loading").modal('hide');
                        swal("网络中断", "拉取数据失败，请检查您的网络配置", "error");
                    }
                });
            },
            "fnServerParams": function (aoData) {
                AddParams(aoData);
            },
            "processing": false,
            "ordering": true,
            "order": [[10, "desc"]],
            "iDisplayLength": 20,
            "ajaxSource": webroot + "/product/productsubcategorylist",
            'aoColumns': [
                {
                    "mData": "productSubCategoryId",
                    "sClass": "detail",
                    "bSortable": false,
                    "mRender": function (data, type, row) {
                        var entity = JSON.stringify(row);
                        var html = "<label class=\"mt-checkbox\"><input name='checkbox' type=\"checkbox\" data-entity='" + entity + "' onclick='rememberCheck(this)' value='' /><span></span></label>";
                        return html;
                    }
                },
                {
                    "mData": "productSubCategoryId",
                    "sClass": "detail",
                    "bSortable": false,
                    "mRender": function (data, type, row) {
                        var entity = JSON.stringify(row);
                        var html=  "<a href='javascript:;' class='btn btn-xs green btn-outline' rel='tooltip' data-entiy='" + entity + "' onclick='editItem(this)'  title='编辑'><i class='fa fa-pencil'></i></a>";
                        return html;
                    }
                },
                {"mData": "productSubCategoryCode", "sClass": "detail"},
                {"mData": "productSubCategoryName", "sClass": "detail"},
                {"mData": "categoryName", "sClass": "detail"},
                {"mData": "subCategoryLevel", "sClass": "detail"},
                {"mData": "paSubCategoryName", "sClass": "detail"},
                {"mData": "saleTaxRate", "sClass": "detail"},
                {"mData": "subCategoryKey", "sClass": "detail"},
                {
                    "mData": "subCategoryState", "sClass": "detail",
                    'mRender': function (data, type, row) {
                        if (row.subCategoryState == 1) {
                            return "有效";
                        }
                        else {
                            return "无效";
                        }
                    }
                },
                {"mData": "lanName", "sClass": "detail"},
            ],
            "aoColumnDefs": [
                {//排序
                    'targets': [-0,1,2,3,4,5,6,7,8,9,10],//不排序的列
                    'orderable': false
                }
            ],
            "fnDrawCallback": function () {
                $('#loading').modal('hide');
            }
        });
    }
    return {
        init: function () {
            if (!jQuery().dataTable) {
                return;
            }
            initDataTable();
        }
    };

    function AddParams(aoData) {
        aoData.push({"name": "productSubCategoryCodeOrName", "value": commonClass.getStringVal($('#productSubCategoryCodeOrName').val())});
        aoData.push({"name": "categoryCode", "value": commonClass.getStringVal($('#categoryCode').val())});
        aoData.push({"name": "lanCode", "value": commonClass.getStringVal($('#lanCode').val())});
        aoData.push({"name": "subCategoryLevel", "value": commonClass.getStringVal($('#subCategoryLevel').val())});
        $('#loading').modal('show');
        $("#chooseAll").removeAttr("checked");
    }
}();

$('.colvis').on('change', function (e) {
    e.preventDefault();
    console.log($(this).attr('data-column'));
    var column = table.column($(this).attr('data-column'));
    column.visible(!column.visible());
});

function checkTime(){
    var startTime =  $('#startTime').val();
    var endTime =  $('#endTime').val();
    if(startTime!="" && endTime!=""){
        if(startTime>endTime){
            return swal("起始更新时间不能大于结束更新时间", "", "warning");
        }
    }

}

$(function () {
    TableDatatablesButtons.init();
    $("#productsubcategorylist_filter").hide();
    loadLanguage("lanCode","");
    getProductSelectItem("categoryCode","subCategoryLevel");
    document.onkeydown = function (e) {
        if (!e) e = window.event;
        if ((e.keyCode || e.which) == 13) {
            event.preventDefault();
            table.fnDraw();
        }
    }

    $(".addItem").click(function () {
        $("#editItem").modal("show");
        $("#colorCardTile").text("新增产品子类");
        $("#productSubCategoryCodes").val("");
        $("#productSubCategoryNames").val("");
        $("#paSubCategoryCodes").val("");
        $("#saleTaxRates").val("");
        $("#subCategoryKeys").val("");
        $("#insertFlag").val(1);
        loadLanguage("lanCodes","");
        getProductSelectItem("categoryCodes","subCategoryLevels");
    });


    $(".btnSyn").click(function () {
        if (productSubCategoryIdList.length == 0) {
            return swal("您没有选择任何选项，无法提交", "", "warning");
        } else {
            $('#syncSubCategoryListDiv').modal('show');
            $("#total").text(productSubCategoryIdList.length);
        };
    });
    $('#productList tbody').on('dblclick', 'tr', function () {
        var aData = table.fnGetData(this);
        var no = aData.productCode;
        var color = aData.colorName;
        location.href = webroot + "/product/psizeuupdate?productCode=" + no + "&color=" + color + "";
    });
});

function saveProductSubCategory() {
    var productSubCategoryId = commonClass.replaceAllQuotationMarks($("#productSubCategoryId").val());
    var productSubCategoryCodes = commonClass.replaceAllQuotationMarks($("#productSubCategoryCodes").val());
    var productSubCategoryNames = commonClass.replaceAllQuotationMarks($("#productSubCategoryNames").val());
    var saleTaxRates = commonClass.replaceAllQuotationMarks($("#saleTaxRates").val());
    var paSubCategoryCodes = commonClass.replaceAllQuotationMarks($("#paSubCategoryCodes").val());
    var lanCodes = commonClass.replaceAllQuotationMarks($("#lanCodes").val());
    var categoryCodes = commonClass.replaceAllQuotationMarks($("#categoryCodes").val());
    var subCategoryLevels = commonClass.replaceAllQuotationMarks($("#subCategoryLevels").val());
    var subCategoryKeys = commonClass.replaceAllQuotationMarks($("#subCategoryKeys").val());

    if(productSubCategoryCodes == "" || productSubCategoryNames == "" || saleTaxRates == "" || lanCodes == "" || categoryCodes == "" || subCategoryLevels == ""){
        return swal("数据未填写完，请检查！", "", "warning");
    }

    if(subCategoryLevels != 1 && paSubCategoryCodes == ""){
        return swal("请填写上级编号！", "", "warning");
    }
    var data = {
        "productSubCategoryId":productSubCategoryId,
        "productSubCategoryCode":productSubCategoryCodes,
        "productSubCategoryName":productSubCategoryNames,
        "paSubCategoryCode":paSubCategoryCodes,
        "saleTaxRate":saleTaxRates,
        "lanCode":lanCodes,
        "categoryCode":categoryCodes,
        "subCategoryLevel":subCategoryLevels,
        "subCategoryKey":subCategoryKeys
    };
    var requestType = "";
    var requestUrl = "";
    var insertFlag = $("#insertFlag").val();
    if(insertFlag == 1){
        requestType = "POST";
        requestUrl = webroot+"/product/insertProductSubCategory";
    }else{
        requestType = "PUT";
        requestUrl = webroot+"/product/updateProductSubCategory";
    }
    $.ajax({
        data: JSON.stringify(data),
        type: requestType,
        url: requestUrl,
        contentType: "application/json;charset=utf-8",
        success:function (response) {
            if(response.code == 200){
                swal({
                        title: "保存成功",
                        text: "",
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: "确定",
                        closeOnConfirm: true
                    },
                    function () {
                        window.location.reload(true);
                    });
            }else{
                return swal(response.message, "", "error");
            }
        },
        error:function () {
            return swal("数据保存失败！", "", "error");
        }

    });

}

//全选按钮
function selectAllCheckBox(curr) {
    //全选按钮是否被选中
    var flag = $(curr).is(":checked");
    if (flag) {
        productSubCategoryIdList.splice(0, productSubCategoryIdList.length);
        $("[name='checkbox']").prop("checked", true);
        $("[name='checkbox']").each(function (index, element) {
            if (index != 0) {
                $(element).attr("checked", true);
                var entiy = $(element).data("entity");
                addSelect(entiy.productSubCategoryId);
            }
        });
    } else {
        productSubCategoryIdList.splice(0, productSubCategoryIdList.length);
        $("[name='checkbox']").prop("checked", false);
    }
}

function rememberCheck(cur) {
    var entity = $(cur).data("entity");
    if ($(cur).is(':checked')) {
        addSelect(entity.productSubCategoryId);
    } else {
        removeSelect(entity.productSubCategoryId);
    }
    if (productSubCategoryIdList.length == $("[name='datatable_length']")) {
        $("#chooseAll").attr("checked", true);
    } else {
        $("#chooseAll").removeAttr("checked");
    }
}

//判断选中的产品中是否还有另外一个  有则忽视  添加
function addSelect(m) {
    for (var i = 0; i < productSubCategoryIdList.length; i++) {
        if (productSubCategoryIdList[i] == m) {
            return;
        }
    }
    productSubCategoryIdList.push(m);
}

//判断选中的产品中是否还有另外一个  删除
function removeSelect(m) {
    for (var i = 0; i < productSubCategoryIdList.length; i++) {
        if (productSubCategoryIdList[i] == m) {
            productSubCategoryIdList.splice(i, 1);
            return;
        }
    }
}


function syncProductSubCategoryList(){
    $('#syncSubCategoryListDiv').modal('hide');
    var productSubCategoryIds = productSubCategoryIdList.join(',');
    var syncSelect = $("#synSetting").val();
    var data = {
        "isFirstLevel": "0",
        "productSubCategoryIds": productSubCategoryIds,
        "syncSelect": syncSelect
    };
    $.ajax({
        type: "POST",
        url: webroot + "/product/syncproductsubcategory",
        data: JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        success: function (response) {
            if (response.code == 200) {
                swal({
                        title: "产品子类同步成功",
                        text: "",
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonTeaxt: "确定",
                        closeOnConfirm: true
                    },
                    function () {
                        if (productSubCategoryIdList != null && productSubCategoryIdList.length != 0) {
                            productSubCategoryIdList.splice(0, productSubCategoryIdList.length);
                        }
                        window.location.reload(true);
                    });
            }
            else {
                swal(response.message, "", "error");
            }
        },
        beforeSend: function (XMLHttpRequest) {
            $('#loading').modal('show');
            $(".btnSyn").attr("disabled", "disabled");
        },
        complete: function (XMLHttpRequest, textStatus) {
            $(".btnSyn").removeAttr("disabled");
            $('#loading').modal('hide');
        },
        error: function () {
            //程序出现未知错误，请稍候重试
            swal(ICICLELangUtil.getText("", 1035), "", "error");
        }
    });
}

function loadLanguage(id,value) {
    $("#"+id).html("");
    $.ajax({
        type: "GET",
        url: webroot + "/hr/getlanguage",
        data: {
        },
        success: function (response) {
            if(response.code==200){
                var ele = "<option value=''>--请选择-</option>";
                var languagelist = response.data;
                for(var i = 0 ;i < languagelist.length; i++){
                    var language = languagelist[i];
                    ele+= "<option value='"+language.lanCode+"'>"+language.lanName+"</option>";
                }
                $("#"+id).append(ele);
                if(value != ""){
                    $("#"+id).val([value]).trigger("change");
                }
            }else{
                swal(ICICLELangUtil.getText("", 1163), ICICLELangUtil.getText("", 1064), "error");
            }

        },
        error: function () {
            swal(ICICLELangUtil.getText("", 1163), ICICLELangUtil.getText("", 1064), "error");
        },
        beforeSend: function (XMLHttpRequest) {
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $('#loading').modal('hide');
        }
    });
}

function getProductSelectItem(param1,param2,value1,value2) {
    $("#"+param1).html("");
    $("#"+param2).html("");
    $.ajax({
        type: "GET",
        url: webroot + "/product/getProductSelectItem",
        data: {
        },
        success: function (response) {
            if(response.code==200){

                var ele = "<option value=''>--请选择-</option>";
                var productCategoryList = response.data.productCategoryList;
                if(productCategoryList!=null && productCategoryList.length>0){
                    for(var i = 0 ;i < productCategoryList.length; i++){
                        var productCategory = productCategoryList[i];
                        ele+= "<option value='"+productCategory.category_key+"'>"+productCategory.category_name+"</option>";
                    }
                    $("#"+param1).append(ele);
                    if(value1 != ""){
                        $("#"+param1).val([value1]).trigger("change");
                    }
                };

                var productCategoryLevelList = response.data.productCategoryLevelList;
                if(productCategoryLevelList!=null && productCategoryLevelList.length>0){
                    var ele = "<option value=''>--请选择-</option>";
                    for(var i = 0 ;i < productCategoryLevelList.length; i++){
                        var productCategoryLevel = productCategoryLevelList[i];
                        ele+= "<option value='"+productCategoryLevel+"'>"+productCategoryLevel+"</option>";
                    }
                    $("#"+param2).append(ele);
                    if(value2 != ""){
                        $("#"+param2).val([value2]).trigger("change");
                    }
                }
            }else{
                return swal(ICICLELangUtil.getText("", 1163), ICICLELangUtil.getText("", 1064), "error");
            }

        },
        error: function () {
            return swal(ICICLELangUtil.getText("", 1163), ICICLELangUtil.getText("", 1064), "error");
        },
        beforeSend: function (XMLHttpRequest) {
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $('#loading').modal('hide');
        }
    });
}


function editItem(cuur) {
    var entiy = $(cuur).data('entiy');
    $("#editItem").modal("show");
    $("#colorCardTile").text("编辑产品子类");
    $("#insertFlag").val(0);
    $("#productSubCategoryId").val(entiy.productSubCategoryId);
    $("#productSubCategoryCodes").val(entiy.productSubCategoryCode);
    $("#productSubCategoryNames").val(entiy.productSubCategoryName);
    $("#paSubCategoryCodes").val(entiy.paSubCategoryCode);
    $("#saleTaxRates").val(entiy.saleTaxRate);
    $("#subCategoryKeys").val(entiy.subCategoryKey);
    loadLanguage("lanCodes",entiy.lanCode);
    getProductSelectItem("categoryCodes","subCategoryLevels",entiy.categoryCode,entiy.subCategoryLevel);
}

function clearNoNum(obj){
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数  
    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
        obj.value= parseFloat(obj.value);
    }
}