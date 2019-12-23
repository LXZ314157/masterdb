/**
 * Created by liurenhua on 2017/12/4.
 */
var productCategoryList = [];
var attributeDefinedList = [];
var currentProductCategoryId = null;
var currentTr = null;
var itemList = [];
var productCategoryAttriList = [];
var tableNameList = [];
var defIdList = [];
$(function () {
    $("#loadUrlDiv").hide();
    init();
    $("#savaProductCategory").click(function () {
        var productCategoryCode = commonClass.replaceAllQuotationMarks($("#productCategoryCode").val());
        var categoryName = commonClass.replaceAllQuotationMarks($("#categoryName").val());
        var categoryDesc = commonClass.replaceAllQuotationMarks($("#categoryDesc").val());
        var loadUrl = productCategoryCode;
        var status = 1;

        if (productCategoryCode.length == 0 || categoryName.length == 0 ) {
            swal(ICICLELangUtil.getText("", 1164), "", "warning");
            return;
        }
        if (!commonClass.checkCode(productCategoryCode)) {
            swal("编码只能为字母和下划线组合", "", "warning");
            return;
        }
        var prodcutCategory;
        if (currentProductCategoryId == null) {
            prodcutCategory = {
                productCategoryCode: productCategoryCode,
                categoryName: categoryName,
                categoryDesc: categoryDesc,
                loadUrl: loadUrl,
                status: status
            }
        } else {
            prodcutCategory = {
                productCategoryId: currentProductCategoryId,
                productCategoryCode: productCategoryCode,
                categoryName: categoryName,
                categoryDesc: categoryDesc,
                loadUrl: loadUrl,
                status: status
            }
        }

        addOrUpdateProductCategory(prodcutCategory);
    });
    //新增属性定义
    $("#addAttribute").click(function () {
        $("#itemCode").val("");
        $("#itemItem").val("");
        $("#itemCode").removeAttr("disabled");

        $("#itemStatus").hide();
        $("#attribute_state").hide();
        //新增
        $(".modal-title").html("新增产品类别属性");
        $("#acode").removeAttr("disabled");
        $("#cname").removeAttr("disabled");
        $("#itemType").removeAttr("disabled");
        $("#itemLength").removeAttr("disabled");
        $("#saveProductCategoryAttrib").val("0");
        $("#attributeDefinedArea").modal('show');
        $("#dataSelectTable").show();
        $("#defKey").val("");
        $("#acode").val("");
        $("#cname").val("");
        $("#modelValue").val("");
        $("#itemLength").val("");
        $("#itemType").val("int");
        $('#itemcontent').hide();
        $("#edititem").html("");
        $("#hastate").addClass("active");
        $("#noSelect input").addClass("active");
        showActive("noSelect", "haveSelect", "haveMoreSelect");
        $("#item_select").show();
        $("#itemName").val("");
        $("#defdesc").val("");
    });
    //新增保存属性
    //保存产品类别属性
    $("#saveProductCategoryAttrib").click(function () {
        var hasItem = $('#stateItem .active input').val();
        var checkValue = $('#itemType  option:selected').val();
        if ($("#defKey").val()=="" || $("#acode").val().length == 0 || $("#cname").val().trim().length == 0 || $("#itemType").val().length == 0 || $("#modelValue").val().length == 0) {
            //信息没有填写完全，无法提交
            swal(ICICLELangUtil.getText("", 1164), "", "warning");
            return;
        }

        if (!commonClass.checkCode($("#acode").val())) {
            swal("编码只能为字母和下划线组合", "", "warning");
            return;
        }
        if (checkValue == "bit") {
            if (hasItem != 1) {
                swal("bit类型需选择单选", "", "warning");
                return;
            }

        }

        if (checkValue == "money") {
            if ($("#itemLength").val()!="0") {
                return swal("money类型值长度必须为0", "", "warning");
            }
        }

        if (hasItem == 2 && (itemList == null || itemList.length == 0)) {
            //有选项须填写选项名
            swal(ICICLELangUtil.getText("", 1106), "", "warning");
            return;
        }

        if (hasItem == 1 && (itemList == null || itemList.length == 0) && checkValue == "int") {
            //有选项须填写选项名
            swal(ICICLELangUtil.getText("", 1106), "", "warning");
            return;
        }
        if (hasItem != 0 && (itemList.length == 0) && $("#itemTable").val() == 0 && checkValue != 'bit') {
            swal("单选或多选时，可选表和选项添加至少填写一项", "", "warning");
            return;
        }

        if ($("#itemLength").val().length == 0) {
            $("#itemLength").val(0);
        }
        if (!commonClass.checkInt($("#itemLength").val())) {
            swal("值长度必须为整数", "", "warning");
            return;
        }

        if ($(this).val() == 0) {
            addAttributeDef(hasItem);
        } else {
            updateAttributeDef(hasItem);
        }

    });
    //添加选项
    $("#btnAddAttribute").click(function () {
        var hasItem = $('#stateItem .active input').val();
        var singleOrMuity = $("#singleOrMuity").val();
        if(singleOrMuity=="1"){//单选
            if ($("#itemCode").val() == null || $("#itemCode").val() == "") {
                //选项名未填写，请输入
                swal("编码未填写，请输入", "", "warning");
                return;
            }
        }
        if ($("#itemName").val() == "") {
            //选项名未填写，请输入
            swal("选项名未填写，请输入", "", "warning");
            return;
        }
        if(singleOrMuity=="2"){
            $("#itemCodeTh").hide();
        }else{
            $("#itemCodeTh").show();
        }
        //修改
        if ($(this).html() == ICICLELangUtil.getText("", 1252)) {
            $("#edititem").html("");
            for (var i = 0; i < itemList.length; i++) {
                if (itemList[i].code == $("#itemCode").val()) {
                    itemList[i].name = $("#itemName").val();
                    if ($("#itemStatus .active input").val() == 0) {
                        itemList[i].status = false;
                    }
                    else {
                        itemList[i].status = true;
                    }
                }
            }
            drawItem(itemList);
            $("#itemCode").val("");
            $("#itemName").val("");
            $(this).html("添加");
            $("#itemStatus").hide();
            if(hasItem!=2){
                $("#haveSelect").addClass("active");
            }
            $("#itemCode").removeAttr("disabled");
            $("#itemName").removeAttr("disabled");

        } else {
            for (var i = 0; i < itemList.length; i++) {
                if(singleOrMuity=="1"){
                    if (itemList[i].code == $("#itemCode").val()) {
                        //当前选项已存在，请换个选项名
                        swal("当前编码已存在，请换个编码", "", "warning");
                        return;
                    }
                }
                if (itemList[i].name == $("#itemName").val()) {
                    //当前选项已存在，请换个选项名
                    swal(ICICLELangUtil.getText("", 1104), "", "warning");
                    return;
                }
            }
            var idOrCode = singleOrMuity=="2"?0:$("#itemCode").val();
            itemList.push({
                status: $("#itemStatus .active input").val(),
                id: idOrCode,
                code: idOrCode,
                name: $("#itemName").val(),
            });
            addItem($("#itemCode").val(), $("#itemName").val(),singleOrMuity);
            $("#itemCode").val("");
            $("#itemName").val("");
            $("#itemDesc").val("");
        }
        $("#itemCode").val("");
    });
    //取消添加
    $("#addCancel").click(function () {
        $("#itemName").val("");
        $("#itemName").removeAttr("disabled");
        $("#itemStatus").hide();
        $("#btnAddAttribute").html("添加");
    });
    //清空itemlist
    $("#attributeCancel").click(function () {
        itemList = [];
        $("#itemTable").val(0);
        $("#itemName").val("");
        $("#btnAddAttribute").html("添加")
    })
    $(".close").click(function () {
        $("#itemName").val("");
        $("#btnAddAttribute").html("添加");
        $("#itemTable").val(0);
        itemList = [];
    });
    $("#haveSelect").click(function () {
        var vs = $('#itemType  option:selected').val();
        if (vs == "bit") {
            showOrHideItem(false,0);
        }
        else {
            showOrHideItem(true,1);
        }
    })
});
init = function () {
    $.ajax({
        type: "post",
        url: webroot + "/product/listattributeitem",
        success: function (response) {
            if (response.code == 200) {
                productCategoryList = response.data;
                for (var i = 0; i < productCategoryList.length; i++) {
                    var productCategory = productCategoryList[i];
                    addProductCategory(productCategory);
                }

            }
            else {
                //失败
                swal(ICICLELangUtil.getText("", 1148), response.message, "error");
            }
        },
        error: function () {
            //您的网络似乎出了一些问题
            swal(ICICLELangUtil.getText("", 1105), "", "error");
        }
    });
    $.ajax({
        type: "post",
        url: webroot + "/product/listattributedefined",
        success: function (response) {
            if (response.code == 200) {
                attributeDefinedList = response.data;
                checkAndDrawConnectDef(attributeDefinedList);
                listProductCategoryAttri();
            }
            else {
//失败
                swal(ICICLELangUtil.getText("", 1148), response.message, "error");
            }
        },
        error: function () {
        }
    });
    $.ajax({
        type: "GET",
        url: webroot + "/product/loadtablename",
        success: function (response) {
            if (response.code == 200) {
                tableNameList = response.data;
                loadTableName();
            }
            else {
                //失败
                swal(ICICLELangUtil.getText("", 1148), response.message, "error");
            }
        },
        error: function () {
        }
    });
}


//验证和绘制属性定义表
function checkAndDrawConnectDef(attributeDefinedList) {
    $.ajax({
        type: "post",
        url: webroot + "/dimension/connectdefid",
        success: function (msg) {
            if (msg.code == 200) {
                defIdList = msg.data;
                drawDefAttribute(attributeDefinedList);
            }
            else {
                //失败
                swal(ICICLELangUtil.getText("", 1148), response.message, "error");
            }
        },
        error: function () {
        }
    });
}


//加载属性定义表
function drawDefAttribute(attributeDefinedList) {
    if (attributeDefinedList != null) {
        for (var i = 0; i < attributeDefinedList.length; i++) {
            var attributeDefined = attributeDefinedList[i];

            if (checkConnect(attributeDefined.id, defIdList) > 0) {
                //已经使用
                addAttrDefined(attributeDefined, true);
            }
            else {
                addAttrDefined(attributeDefined, false);
            }
        }
    }
}

//新增产品类别属性
function addAttributeDef(hasItem) {
    var status = 1;
    status = $('#status .active input').val();
    var requestData = {
        "defKey": commonClass.replaceAllQuotationMarks($("#defKey").val()),
        "defCode": commonClass.replaceAllQuotationMarks($("#acode").val()),
        "defName": commonClass.replaceAllQuotationMarks($("#cname").val()),
        "defDesc": commonClass.replaceAllQuotationMarks($("#defdesc").val()),
        "defType": commonClass.replaceAllQuotationMarks($("#itemType").val()),
        "defLength": commonClass.replaceAllQuotationMarks($("#itemLength").val()),
        "isSync": commonClass.replaceAllQuotationMarks($("#isSync").val()),
        "defGroup": commonClass.replaceAllQuotationMarks($("#defGroup").val()),
        "modelValue": commonClass.replaceAllQuotationMarks($("#modelValue").val()),
        "hasItem": hasItem,
        "status": status,
        "itemList": itemList
    };
    if ($("#itemTable").val() != 0) {
        requestData["selectTable"] = $("#itemTable").val();
    }
    $.ajax({
        type: "POST",
        url: webroot + "/product/addattributedefine",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(requestData),
        success: function (data) {
            if (data.code == 200) {
                swal({
                        //成功
                        //添加成功，确定
                        title: ICICLELangUtil.getText("", 1005),
                        text: ICICLELangUtil.getText("", 1055),
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: ICICLELangUtil.getText("", 1007),
                        closeOnConfirm: true
                    },
                    function () {
                        location.reload();
                    });
            } else {
                //失败
                swal(ICICLELangUtil.getText("", 1148), data.message, "error");
            }
        },
        error: function () {
            //发送失败
            //网络中断
            swal(ICICLELangUtil.getText("", 1163), ICICLELangUtil.getText("", 1064), "error");
        },
        beforeSend: function (XMLHttpRequest) {
            $("#saveProductCategoryAttrib").attr("disabled", "disabled");
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#saveProductCategoryAttrib").removeAttr("disabled");
            $('#loading').modal('hide');
        }
    });
}

//更新产品类别属性
function updateAttributeDef(hasItem) {
    var status = 1;
    status = $('#status .active input').val();
    var requestData = {
        "id": commonClass.replaceAllQuotationMarks($("#id").val()),
        "defKey": commonClass.replaceAllQuotationMarks($("#defKey").val()),
        "defCode": commonClass.replaceAllQuotationMarks($("#acode").val()),
        "defName": commonClass.replaceAllQuotationMarks($("#cname").val()),
        "defDesc": commonClass.replaceAllQuotationMarks($("#defdesc").val()),
        "defType": commonClass.replaceAllQuotationMarks($("#itemType").val()),
        "defLength": commonClass.replaceAllQuotationMarks($("#itemLength").val()),
        "isSync": commonClass.replaceAllQuotationMarks($("#isSync").val()),
        "defGroup": commonClass.replaceAllQuotationMarks($("#defGroup").val()),
        "modelValue": commonClass.replaceAllQuotationMarks($("#modelValue").val()),
        "hasItem": hasItem,
        "status": status,
        "itemList": itemList
    };
    if ($("#itemTable").val() != 0) {
        requestData["selectTable"] = $("#itemTable").val();
    }
    $.ajax({
        type: "PUT",
        contentType: "application/json;charset=utf-8",
        url: webroot + "/product/updateattributedefine",
        data: JSON.stringify(requestData),
        success: function (response) {
            if (response.code == 200) {
                swal({
                        //成功
                        //更新成功，确定
                        title: ICICLELangUtil.getText("", 1005),
                        text: ICICLELangUtil.getText("", 1167),
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: ICICLELangUtil.getText("", 1007),
                        closeOnConfirm: true
                    },
                    function () {
                        location.reload();
                    });
            } else {
                //失败
                swal(ICICLELangUtil.getText("", 1148), response.message, "error");
            }
        },
        error: function () {
            //发送失败
            //网络中断
            swal(ICICLELangUtil.getText("", 1163), ICICLELangUtil.getText("", 1064), "error");
        },
        beforeSend: function (XMLHttpRequest) {
            $("#saveProductCategoryAttrib").attr("disabled", "disabled");
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#saveProductCategoryAttrib").removeAttr("disabled");
            $('#loading').modal('hide');
        }
    });
}


function addProductCategory(productCategory) {
    if (productCategory.categoryState == 1) {
        var html = "<tr><td class='detail' width='5%'>" + productCategory.productCategoryId + "</td>";
        html += "<td class='detail'>" + productCategory.productCategoryCode + "</td>";
        html += "<td class='detail'>" + productCategory.categoryName + "</td>"
        html += "<td class='detail' >" + productCategory.categoryDesc + "</td>"
        html += "<td><a href='javascript:;'  data-entity='" + JSON.stringify(productCategory) + "' class='btn btn-xs green btn-outline' " +
            "rel='tooltip' title='编辑' onclick='showProductCategory(this)'><i class='fa fa-pencil'></i></a>"
        html += "<a href='javascript:;' data-entity='" + JSON.stringify(productCategory) + "' class='btn btn-xs blue btn-outline' " +
            "rel='tooltip' title='查看关联属性' onclick='showAttribue(this)'><i class='fa fa-balance-scale'></i></a> "
        html += "<a href='javascript:;' data-entity='" + JSON.stringify(productCategory) + "' class='btn btn-xs red btn-outline' " +
            "rel='tooltip' title='关联新属性' onclick='connectAttribute(this)'><i class='fa fa-adjust'></i></a> "
        html += "<a href='javascript:;' data-entity='" + JSON.stringify(productCategory) + "' class='btn btn-xs yellow btn-outline' " +
            "rel='tooltip' title='同步' onclick='sync2Burgeon(this)'><i class='fa fa-exchange'></i></a> "
        html += "<a href='javascript:;' data-entity='" + JSON.stringify(productCategory) + "' class='btn btn-xs blue btn-outline'" +
            "rel='tooltip' title='查看列表' onclick='viewList(this)'><i class='fa fa-columns'></i></a></td> "
        $("#productCategory").append(html);
    }

}

function showAttribue(cur) {
    listProductCategoryAttri();
    var productCategoryDetail = $(cur).data('entity');
    $("#categoryName").val(productCategoryDetail.categoryName);
    $("#productCategoryId").val(productCategoryDetail.productCategoryId);
    $("#productCategoryCode").val(productCategoryDetail.productCategoryCode);
    $("#attributeCode").val(productCategoryDetail.productCategoryCode);
    $("#productName").val(productCategoryDetail.categoryName);
    $("#checklist").html(" ");

    var flag = false;

    for (var i = 0; i < attributeDefinedList.length; i++) {
        var html = "";
        var attributeDefined = attributeDefinedList[i];
        if (attributeDefined.status == 0)
            continue;
        if (contain(productCategoryDetail.productCategoryId, attributeDefined.id)) {
            flag = true;
            var html = "<label class=\"mt-checkbox col-md-6\">" + attributeDefined.defName;
            html += "<input name='checkbox' type=\"checkbox\"  checked value='" + attributeDefined.id + "' onclick=\"return false;\"/>";
            html += "<span></span></label>";
        }
        $("#checklist").append(html);
    }
    if (!flag) {
        //您没有关联任何属性
        swal(ICICLELangUtil.getText("", 1076), "", "warning");
        $("#connectAttribute").modal('hide');
        return;
    }
    else {
        $("#connectAttribute").modal('show');
    }
    $("#btnConnect").hide();

}

function addAttrDefined(attrDefined, flag) {
    // if (attrDefined.status == 1) {
        var html = "<tr><td class='detail'>" + attrDefined.defCode + "</td>"
        html += "<td class='detail'>" + attrDefined.defName + "</td>"
        html += "<td class='detail'>" + attrDefined.defType + "</td>"
        html += "<td class='detail'>" + attrDefined.defLength + "</td>"
        if (attrDefined.hasItem == 1) {
            html += "<td class='detail'>有单选项</td>"
        }
        else if (attrDefined.hasItem == 2) {
            html += "<td class='detail'>有多选项</td>"
        }
        else {
            html += "<td class='detail'>无选项</td>"
        }

        if (attrDefined.status == 1) {
            html += "<td class='detail'>有效</td>"
        }else{
            html += "<td class='detail'>无效</td>"
        }

        html += "<td>"
        if (!flag) {
            //可以编辑 false
            html += "<a href='javascript:;'  onclick='editDimensionAttribute(this,false)' class='btn btn-xs green btn-outline'" +
                " rel='tooltip' title='编辑' data-entiy='" + JSON.stringify(attrDefined) + "'><i class='fa fa-pencil'></i></a>"
        }
        else {
            //true 不能编辑
            html += "<a href='javascript:;'  onclick='editDimensionAttribute(this,true)' class='btn btn-xs green btn-outline'" +
                " rel='tooltip' title='编辑' data-entiy='" + JSON.stringify(attrDefined) + "'><i class='fa fa-pencil'></i></a>"
            // html += "<a href='javascript:;'  class='btn btn-xs grey btn-outline'" +
            //     " rel='tooltip' title='无法编辑' disabled='disabled'><i class='fa fa-pencil'></i></a>"
        }
        html += "</td></tr>"
        $("#attrDefined").append(html);
    // }
}

//验证属性是否被关联
function checkConnect(id, defIdList) {
    var result = $.grep(defIdList, function (obj) {
        return id == obj;
    });
    return result.length;
}

//已关联过的属性不能再次编辑
function editAttributeUnDisabled(flag) {
    if (flag) {
        $("#cname").attr("disabled", "disabled");
        $("#itemType").attr("disabled", "disabled");
        $("#itemLength").attr("disabled", "disabled");
        $("#hastate").addClass("active");
        $("#nostate").removeClass("active");
        $("#attribute_state").hide();
    }
    else {
        $("#cname").removeAttr("disabled");
        $("#itemType").removeAttr("disabled");
        $("#itemLength").removeAttr("disabled");
        $("#hastate").addClass("active");
        $("#nostate").removeClass("active");
        $("#attribute_state").show();
    }

}

//显示新增或者编辑的浮动框
function showProductCategory(curr) {
    currentTr = curr;
    $("#producCategorytArea").modal('show');
    if (curr == null) {
        $("#loadUrlDiv").hide();
        currentProductCategoryId = null;

        $("#productCategoryStatus").hide();

        $(".modal-title").html("新增产品类别");
        $("#productCategoryCode").val("");
        $("#productCategoryCode").removeAttr("disabled");
        $("#categoryDesc").val("");
        $("#categoryName").val("");

    } else {
        // $("#productCategoryStatus").show();
        $("#loadUrlDiv").show();
        $(".modal-title").html("编辑产品");
        $("#valid").removeClass("active");
        $("#inValid").removeClass("active");
        $("#loadUrl").attr("disabled","disabled");

        var productCategory = $(curr).data("entity");

        if (productCategory.categoryState=='1') {
            $("#valid").addClass("active");
        } else {
            $("#inValid").addClass("active");
        }

        $("#productCategoryCode").val(productCategory.productCategoryCode);
        $("#productCategoryCode").attr("disabled", true);
        $("#categoryDesc").val(productCategory.categoryDesc);
        $("#categoryName").val(productCategory.categoryName);
        $("#loadUrl").val(productCategory.loadUrl);
        $("#productName").val(productCategory.categoryName);
        currentProductCategoryId = productCategory.productCategoryId;
    }
}

//添加或更新产品类别
function addOrUpdateProductCategory(prodcutCategory) {
    var requestUrl;
    var requestType;
    var requestData = JSON.stringify(prodcutCategory);


    if (currentProductCategoryId == null) {
        requestType = "post";
        requestUrl = webroot + "/product/addproductcategory"
    } else {
        requestType = "put";
        requestUrl = webroot + "/product/updateproductcategory"
    }

    $.ajax({
        type: requestType,
        url: requestUrl,
        data: requestData,
        contentType: "application/json;charset=utf-8",
        success: function (response) {
            if (response.code == 200) {
                var dimension = response.data;
                swal({
                        //成功，确定
                        title: ICICLELangUtil.getText("", 1005),
                        text: "",
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: ICICLELangUtil.getText("", 1007),
                        closeOnConfirm: true
                    },

                    function () {
                        location.reload();
                    }
                );
            }
            else {
                //失败
                swal(ICICLELangUtil.getText("", 1148), response.message, "error");
            }
        },
        error: function () {
            //发送失败
            //网络中断
            swal(ICICLELangUtil.getText("", 1163), ICICLELangUtil.getText("", 1064), "error");
        }
    });
}


//添加或更新属性定义
function addOrUpdateDefined(curr) {
    $("#attributeDefinedArea").modal('show');
}

function showOrHideItem(flag,itemId) {
    //有选项
    if (flag) {
        $("#dataSelectTable").show();
        $("#itemcontent").show();
        $("#itemStatus").hide();
        $("#itemName").removeAttr("disabled");
        //$("#itemTable").val(0);
        if(itemId==1){
            $("#itemCodeDiv").show();
            $("#itemCodeTh").show();
            $("#singleOrMuity").val("1");

        }else{
            $("#itemCodeDiv").hide();
            $("#itemCodeTh").hide();
            $("#singleOrMuity").val("2");
        }
    }
    else {
        $("#itemcontent").hide();
        $("#itemStatus").hide();
        $("#dataSelectTable").hide();
        $("#itemTable").val(0);
    }
}

function showOrHideSelect(flag) {
    //选下拉框
    if (flag) {
        $("#dataSelectTable").show();
        $("#itemcontent").hide();
        $("#itemStatus").hide();
        $("#itemName").removeAttr("disabled");
    }
    else {
        $("#dataSelectTable").hide();
        $("#itemcontent").show();
        $("#itemStatus").hide();
        $("#itemName").removeAttr("disabled");
    }
}

//绘制选项表格
function drawItem(itemList) {
    var singleOrMuity = $("#singleOrMuity").val();
    for (var i = 0; i < itemList.length; i++) {
        var item = itemList[i];
        var html = "<tr>";
        if(singleOrMuity=="1"){
            html += "<td class=\"text-center\">" + item.code + "</td>";
        }
        html += "<td class=\"text-center\">" + item.name + "</td>";
        if (item.status == 1)
            html += "<td class=\"text-center\">有效</td>";
        else
            html += "<td class=\"text-center\">无效</td>";
        html += "<td class=\"text-center\"><a href='javascript:;' class='btn btn-xs green btn-outline' data-index='" + i + "' data-entiy='" + JSON.stringify(item) + "' rel='tooltip' title='编辑' onclick='updateItem(this)'><i class='fa fa-pencil'></i></a></td></tr>";
        $("#edititem").append(html);
    }
}

//单条添加选项
function addItem(itemCode,itemName,singleOrMuity) {
    var index = itemList.length - 1;
    var html = "<tr>";
    if(singleOrMuity=="1"){
        html += "<td class=\"text-center\">" + itemCode + "</td>";
    }
    html += "<td class=\"text-center\">" + itemName + "</td>";
    html += "<td class=\"text-center\">有效</td>";
    html += "<td class=\"text-center\"><a href='javascript:;' class='btn btn-xs green btn-outline' data-index='" + index + "' rel='tooltip' title='删除' onclick='deleteItem(this)'><i class='fa fa-remove'></i></a></td></tr>";
    $("#edititem").append(html);

}

//删除选项
function deleteItem(curr) {
    itemList.splice($(curr).data("index"), 1);
    $(curr).parent().parent().remove();
}

//更新已有选项
function updateItem(curr) {
    //修改
    $("#btnAddAttribute").html(ICICLELangUtil.getText("", 1252));
    $("#itemStatus").show();
    var entiy = $(curr).data(entiy);
    $("#itemName").val(entiy.entiy.name);
    $("#itemCode").val(entiy.entiy.code);
    $("#itemStatus_0").removeClass("active");
    $("#itemStatus_1").removeClass("active");
    if (entiy.entiy.status = true) {
        $("#itemStatus_1").addClass("active");
    }
    else {
        $("#itemStatus_0").addClass("active");
    }
    $("#itemCode").attr("disabled", "true");
}

//编辑属性
function editDimensionAttribute(curr, flag) {
    $("#saveProductCategoryAttrib").val("1");
    //编辑产品类别属性
    $(".modal-title").html("编辑产品属性");
    var data = $(curr).data("entiy");
    $("#id").val(data.id);
    $("#defKey").val(data.defKey);
    $("#acode").val(data.defCode);
    $("#acode").attr("disabled", "disabled");
    $("#cname").val(data.defName);
    $("#itemType").val(data.defType);
    $("#itemLength").val(data.defLength);
    $("#defdesc").val(data.defDesc);
    $("#defGroup").val(data.defGroup);
    $("#modelValue").val(data.modelValue);
    $("#isSync").val(data.sync==true?1:0);

    if (data.status==1) {
        $("#hastate").addClass("active");
        $("#nostate").removeClass("active");
    } else {
        $("#hastate").removeClass("active");
        $("#nostate").addClass("active");
    }
    // 多选
    if (data.hasItem == 2) {
        showActive("haveMoreSelect", "noSelect", "haveSelect");
        $("#itemStatus").hide();
        $("#itemName").removeAttr("disabled")
        showOrHideItem(true,2);

    }
    //单选
    else if (data.hasItem == 1) {
        var vs = $('#itemType  option:selected').val();
        if (data.defType == "bit") {
            showOrHideItem(false,0);
        }
        else {
            showOrHideItem(true,1);
        }
        showActive("haveSelect", "noSelect", "haveMoreSelect");
    }
    //无选项
    else {
        showActive("noSelect", "haveSelect", "haveMoreSelect");
        $("#itemcontent").hide();
        showOrHideItem(false,0);
        $("#edititem").html("");
    }
    if (data.selectTable == null || data.selectTable == "") {
        $("#dataSelectTable").hide();

    }
    else {
        $("#itemTable").val(data.selectTable);
        $("#dataSelectTable").show();
    }
    if (flag) {
        editAttributeUnDisabled(true);
    }
    else {
        editAttributeUnDisabled(false);
    }
    $("#item_select").show();
    $("#attributeDefinedArea").modal('show');
    loadItem(data.id);
    $("#itemCode").val("");
    $("#itemItem").val("");
    $("#itemCode").removeAttr("disabled");
}

//更改有选项的状态
function showActive(activePart, unActiveBefore, unActiveAfter) {
    $("#" + activePart).addClass("active");
    $("#" + unActiveBefore).removeClass("active");
    $("#" + unActiveAfter).removeClass("active");
}

//如果有选项从服务器获取下拉选项
function loadItem(id) {
    $("#edititem").html("");
    $.ajax({
        type: "get",
        url: webroot + "/product/finddefinedbyId",
        data: {
            id: id
        },
        success: function (response) {
            if (response.code == 200) {
                itemList = response.data;
                drawItem(itemList);
            }
            else {
                $("#itemcontent").hide();
            }
        },
        error: function () {
            //发送失败
            //网络中断
            swal(ICICLELangUtil.getText("", 1163), ICICLELangUtil.getText("", 1064), "error");
        }
    });
}

//产品类别关联属性
function connectAttribute(cur) {
    listProductCategoryAttri();
    var productCategoryDetail = $(cur).data('entity');
    $("#categoryName").val(productCategoryDetail.categoryName);
    $("#productCategoryCode").val(productCategoryDetail.productCategoryCode);
    $("#productCategoryId").val(productCategoryDetail.productCategoryId);
    $("#attributeId").val(productCategoryDetail.productCategoryId);
    $("#attributeCode").val(productCategoryDetail.productCategoryCode);
    $("#productName").val(productCategoryDetail.categoryName);
    $("#checklist").html(" ");

    var flag = false;
    for (var i = 0; i < attributeDefinedList.length; i++) {
        var html = "";
        var attributeDefined = attributeDefinedList[i];
        if (attributeDefined.status == 0)
            continue;

        if (!contain(productCategoryDetail.productCategoryId, attributeDefined.id)) {
            flag = true;
            html = "<label class=\"mt-checkbox col-md-6\">" + attributeDefined.defName;
            html += "<input  type=\"checkbox\" name='"+attributeDefined.defCode+"'  title='"+attributeDefined.defName+"'  value='" + attributeDefined.id + "' />";
            html += "<span></span></label>";
        }
        $("#checklist").append(html);
    }

    if (!flag) {
        swal("您没有任何属性可以关联", "", "warning");
        $("#connectAttribute").modal('hide');
        return;
    }
    else {
        $("#connectAttribute").modal('show');
    }
    $("#btnConnect").show();
}

//验证是否有关联
function contain(productCategoryId, attributeDefId) {
    if (productCategoryAttriList != null) {
        for (var i = 0; i < productCategoryAttriList.length; i++) {
            var productCategoryAttri = productCategoryAttriList[i];
            if (productCategoryAttri.productCategoryId == productCategoryId) {
                if (productCategoryAttri.productAttributeDefineId == attributeDefId && productCategoryAttri.categoryAttributeState == 1)
                    return true;
            }
        }
        return false;
    }
    else {
        return false;
    }
};

//发送属性关联请求
function attributeRelevant() {
    var checkList = [];
    $('#checklist label input[type=checkbox]').each(function (i) {
        if ($(this).prop('checked')) {
            checkList.push({
                "productCategoryId": $("#attributeId").val(),
                "productAttributeDefineId": $(this).val(),
                "defCode": $(this).attr("name"),
                "defName": $(this).attr("title")
            });
        }
    });
    if (checkList == null || checkList.length == 0) {
        swal("您还没有关联任何属性", "", "warning");
        return;
    }
    var requestData = {
        "productCategoryId": $("#attributeId").val(),
        "productCategoryCode": $("#attributeCode").val(),
        "productCategoryAttributeList": checkList
    };

    $.ajax({
        type: "POST",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(requestData),
        url: webroot + "/product/attribconnect",
        success: function (response) {
            if (response.code == 200) {
                swal({
                        //成功，属性关联成功
                        title: ICICLELangUtil.getText("", 1005),
                        text: ICICLELangUtil.getText("", 1046),
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: ICICLELangUtil.getText("", 1007),
                        closeOnConfirm: true
                    },
                    function () {
                        location.reload();
                    });
            } else {
                swal(response.message, "", "error");
            }
        },
        beforeSend: function (XMLHttpRequest) {
            $("#btnConnect").attr("disabled", "disabled");
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#btnConnect").removeAttr("disabled");
            $('#loading').modal('hide');
        },
        error: function () {
            //程序出现未知错误，请稍候重试
            swal(ICICLELangUtil.getText("",1035), "", "error");
        }
    });
}


function viewList(curr) {
    var productCategory = $(curr).data("entity");
    var loadUrl = productCategory.loadUrl;
    location.href = webroot + "/product/getproductcategoryList?loadUrl="+loadUrl;
}


function sync2Burgeon(cur){
    var productCategoryDetail = $(cur).data('entity');
    var id = productCategoryDetail.productCategoryId;
    var categoryKey = productCategoryDetail.categoryKey;
    var categoryName = productCategoryDetail.categoryName;
    var data = {
        "id": id,
        "categoryKey": categoryKey,
        "categoryName": categoryName,
        "isFirstLevel": "1"
    };
    $.ajax({
        type: "POST",
        url: webroot + "/product/syncproductsubcategory",
        data: JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        success: function (response) {
            if (response.code == 200) {
                swal({
                        title: "产品类别同步成功",
                        text: "",
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonTeaxt: "确定",
                        closeOnConfirm: true
                    },
                    function () {
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

// 加载所有表名
function loadTableName() {
    var html = "";
    html = "<option value='" + 0 + "'>请选择</option>";
    for (var i = 0; i < tableNameList.length; i++) {
        var content = tableNameList[i];
        if (content.indexOf("select_uom") >= 0) {
            continue;
        }
        html += "<option value='" + content + "'>" + content + "</option>";

    }
    $("#itemTable").html(html);
}

//下拉选项改变 （针对bit）
function showAllItemTable() {
    var vs = $('#itemType  option:selected').val();
    if (vs == "bit") {
        $("#dataSelectTable").hide();
        showActive("haveSelect", "noSelect", "haveMoreSelect");
        $("#item_select").hide();
        $("#itemcontent").hide();
        $("#itemTable").val(0);
    }
    else {
        $("#item_select").show();
    }
}


//
function listProductCategoryAttri() {
    $.ajax({
        type: "post",
        url: webroot + "/product/listproductcategoryattri",
        success: function (response) {
            if (response.code == 200) {
                productCategoryAttriList = response.data;
            }
        },
        error: function () {
        }
    });
}



