var workgroup = [];
var clothestype = [];
var codestandard = [];
var naturesenson = [];
var wave = [];
var band = [];
var catezl = [];
var catexl = [];
var brand = [];
var group = [];
var line = [];
var viewSelectproductinfoList = [];
var allCategoryLevel2List = [];
var categoryLevel1List = [];
var categoryLevel2List = [];
var productAttribDefList = [];
var attributelist = [];
var sizeGroup = [];
var year = [];
var uom = [];
var standard = [];
var devSeasons = [];
var productCode = commonClass.getQueryString("productCode").toUpperCase();
var productCategoryCode = commonClass.getQueryString("productCategoryCode").toUpperCase();

var isCategory;
$(function () {
    if(productCategoryCode==null || productCategoryCode=="" || productCategoryCode=="P04"){
        isCategory = 0;//服饰
    }else{
        isCategory = 1;//图书、家居、物料
    }
    $("#attrdetailDiv").hide();
    $("#smt").hide();
    //加载下拉框数据
    loadDropdownList();

    if(isCategory==0){
        $("#attrdetailDiv").show();
        $("#smt").show();
        $("#detailContent").show();
    }else{
        $("#detailContent").hide();
    }
    getAttributeDefinedList(productCategoryCode);

    $("#submit").click(function () {
        var data = {};
        var productcode = $("#productcode").val();
        var wave = commonClass.replaceAllQuotationMarks($("#wave").val());
        var band = commonClass.replaceAllQuotationMarks($("#band").val());
        var group = commonClass.replaceAllQuotationMarks($("#icicle_group").val());
        var line = commonClass.replaceAllQuotationMarks($("#line").val());
        var itemlist = [];

        var syncstatus = commonClass.replaceAllQuotationMarks($("#syncstatus").val());
        if(syncstatus==""){
            return swal("请选择当前状态", "", "warning");
        }

        var status = commonClass.replaceAllQuotationMarks($("#status").val());
        if(status==""){
            return swal("请选择可用状态", "", "warning");
        }
        var baseAttribute = {
            "productCode": commonClass.replaceAllQuotationMarks($("#productcode").val()),
            "brand": commonClass.replaceAllQuotationMarks($("#brand").val()),
            "unitPrice": commonClass.replaceAllQuotationMarks($("#price").val()),
            "productName": commonClass.replaceAllQuotationMarks($("#pname").val()),
            "syncStatus": commonClass.replaceAllQuotationMarks($("#syncstatus").val()),
            "cateZl": commonClass.replaceAllQuotationMarks($("#catezl").val()),
            "status": commonClass.replaceAllQuotationMarks($("#status").val()),
            "cateXl": commonClass.replaceAllQuotationMarks($("#catexl").val()),
            "uom": commonClass.replaceAllQuotationMarks($("#uom").val()),
        };
        var item = "";
        if(isCategory==0){
            //第二部分（服装）
            var securityCode = commonClass.replaceAllQuotationMarks($("#productno").val());
            var orderNo = commonClass.replaceAllQuotationMarks($("#order_no").val());
            var devNo = commonClass.replaceAllQuotationMarks($("#dev_no").val());
            var styleNo = commonClass.replaceAllQuotationMarks($("#style_no").val());
            var modelNo = commonClass.replaceAllQuotationMarks($("#model_no").val());
            var materialNo = commonClass.replaceAllQuotationMarks($("#material_no").val());
            var materialName = commonClass.replaceAllQuotationMarks($("#material_name").val());
            var colorName = commonClass.replaceAllQuotationMarks($("#color_name").val());
            var productClass = commonClass.replaceAllQuotationMarks($("#product_class").val());
            var natureSeason = commonClass.replaceAllQuotationMarks($("#nature_season").val());
            var salesDate = commonClass.replaceAllQuotationMarks($("#sales_date").val());
            var colorCardNo = commonClass.replaceAllQuotationMarks($("#color_card_no").val());
            var colorCardName = commonClass.replaceAllQuotationMarks($("#color_card_name").val());
            var workgroup = commonClass.replaceAllQuotationMarks($("#work_group").val());
            var sizegroup = commonClass.replaceAllQuotationMarks($("#size_group").val());
            var code = commonClass.replaceAllQuotationMarks($("#code").val());
            var standard = commonClass.replaceAllQuotationMarks($("#standard").val());
            var batch = commonClass.replaceAllQuotationMarks($("#batch").val());
            var styleRule = commonClass.replaceAllQuotationMarks($("#style_rule").val());
            var supplier = commonClass.replaceAllQuotationMarks($("#supplier").val());
            var year = commonClass.replaceAllQuotationMarks($("#year").val());
            var devseason = commonClass.replaceAllQuotationMarks($("#dev_season").val());
            var estimatedRate = commonClass.replaceAllQuotationMarks($("#estimated_rate").val());
            var colourSystem = commonClass.replaceAllQuotationMarks($("#colour_system").val());
            var materialNameTag = commonClass.replaceAllQuotationMarks($("#material_name_tag").val());
            var opDate = commonClass.replaceAllQuotationMarks($("#op_date").val());
            if (productcode.length == null || productcode.length == 0) {
                return swal("数据填写不完整，请正确填写", "", "warning");
            }
            if (colorCardNo.length != null && colorCardNo.length != 0) {
                if (colorCardName == null || colorCardName.length == 0) {
                    return swal("请正确填写色卡号", "", "warning");
                }

            }
            if (materialNo.length != null && materialNo.length != 0) {
                if (materialName == null || materialName.length == 0) {
                    return swal("请正确填写面料编号", "", "warning");
                }

            }

            item = {
                "productCode": productcode,
                "securityCode": securityCode,
                "orderno": orderNo,
                "devNo": devNo,
                "styleNo": styleNo,
                "modelNo": modelNo,
                "materialNo": materialNo,
                "materialName": materialName,
                "colorName": colorName,
                "productClass": productClass,
                "natureSeason": natureSeason,
                "salesDate": salesDate,
                "colorCardNo": colorCardNo,
                "colorCardName": colorCardName,
                "colourSystem": colourSystem,
                "workGroup": workgroup,
                "sizeGroup": sizegroup,
                "wave": wave,
                "icicleBand": band,
                "icicleGroup": group,
                "devSeason": devseason,
                "line": line,
                "year": year,
                "code": code,
                "standard": standard,
                "batch": batch,
                "styleRule": styleRule,
                "supplier": supplier,
                "estimatedRate": estimatedRate,
                "materialNameTag": materialNameTag,
                "opDate": opDate
            };
        }else{
            for (var i = 0; i < productAttribDefList.productAttributeDefinedVOList.length; i++) {
                var productAttribDef = productAttribDefList.productAttributeDefinedVOList[i];
                if (productAttribDef.hasItem) {
                    itemlist.push({
                        defCode: productAttribDef.defCode,
                        value: $("#" + $.trim(productAttribDef.defCode)).find('option:selected').data('itemcode')
                    });
                }
                else {
                    itemlist.push({
                        defCode: productAttribDef.defCode,
                        value: $("#" + $.trim(productAttribDef.defCode)).val()
                    });
                }

            }

        }
        data = {
            "product":baseAttribute,
            "productAttributeExtend":item,
            "productCategoryAttr":itemlist,
            "isCategory":isCategory,
            "productCategoryCode":productCategoryCode,
            "productCode":productCode
        };

        var requestType = "PUT";
        $('#loading').modal('show');
        $.ajax({
            type: requestType,
            url: webroot + "/product/updatestyle",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data),
            success: function (response) {
                if (response.code == 200) {
                    return swal({
                            title: "修改成功！",
                            text: "",
                            type: "success",
                            showCancelButton: false,
                            confirmButtonClass: "btn-success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        },
                        function () {
                            window.location.href = webroot + "/product/pstyleupdate?productCode=" + productCode+"&isCategory="+isCategory+"&productCategoryCode="+productCategoryCode;
                        });
                }
                else {
                    return swal(response.message, "", "error");
                }
            },
            beforeSend: function (XMLHttpRequest) {
                $('#loading').modal('show');
                $("#submit").attr("disabled", "disabled");
            },
            complete: function (XMLHttpRequest, textStatus) {
                $("#submit").removeAttr("disabled");
                $('#loading').modal('hide');
            }
        });
    });
    $("#cancle").click(function () {
        $("#productno").val('');
    });
    $("#colorcardnumber").change(function () {
        var colorCardNo = commonClass.replaceAllQuotationMarks($("#colorcardnumber").val());
        var colorCardName = commonClass.replaceAllQuotationMarks($("#colorcardname").val());
        $("#colorcardname").val('');
        if (colorCardNo.length != 0) {
            $.ajax({
                type: "POST",
                url: webroot + "/product/checkcolorcard",
                data: {'colorCardCode': colorCardNo},
                success: function (response) {
                    if (response.code == 200) {
                        $("#colorcardname").val(response.message);
                        $("#colorcardname").attr("disabled", "disabled");
                    }
                    else {
                        $("#colorcardname").val('');
                        return swal("色卡格式错误，请重新输入!", "", "warning");
                    }
                }

            });
        }
    });
    $("#materialno").change(function () {
        var materialNo = commonClass.replaceAllQuotationMarks($("#materialno").val());
        $("#materialname").val('');
        if (materialNo.length != 0) {
            $.ajax({
                type: "POST",
                url: webroot + "/product/checkmaterial",
                data: {'materialNo': materialNo},
                success: function (response) {
                    if (response.code == 200) {
                        $("#materialname").val(response.message);
                        $("#materialname").attr("disabled", "disabled");
                    }
                    else {
                        $("#materialname").val('');
                        return swal("面料名错误，请重新输入!", "", "warning");
                    }
                }

            });
        }
    });
});//页面加载完毕
function loadPaper() {
    $.ajax({
        type: "POST",
        url: webroot + "/product/findbyproductcode",
        data:{
            "productCode":productCode,
            "productCategoryCode":productCategoryCode
        },
        success: function (response) {
            if (response.code == 200) {
                attributelist = response.data.productAttributeExtendlist;
                viewSelectproductinfoList = response.data.viewSelectproductinfoList;
                categoryLevel1List = response.data.categoryLevel1List;
                categoryLevel2List = response.data.categoryLevel2List;
                RenderProductData();
            }
            else {
                return swal("数据获取失败，请稍后重试", "", "warning");
            }
        },
        error: function () {
            //程序出现未知错误，请稍候重试
            return swal(ICICLELangUtil.getText("", 1035), "", "error");
        }
    })
}



function loadDropdownList(){
    //加载下拉框
    $.ajax({
        type: "GET",
        url: webroot + "/product/dropdown",
        success: function (response) {
            if (response.code == 200) {
                var resultlist = response.data;
                for (var i = 0; i < resultlist.length; i++) {
                    if (resultlist[i].tableName == "select_band") {
                        band.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_brand") {
                        brand.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "cate_zl") {
                        catezl.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "cate_xl") {
                        catexl.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_wave") {
                        wave.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_work_group") {
                        workgroup.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_clothes_type") {
                        clothestype.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_code_standard") {
                        codestandard.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_nature_season") {
                        naturesenson.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_group") {
                        group.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_line") {
                        line.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_size_group") {
                        sizeGroup.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_uom") {
                        uom.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_standard") {
                        standard.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_dev_season") {
                        devSeasons.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                }
                loadSelectAll(workgroup, "workgroup");
                loadSelectAll(clothestype, "productclass");
                loadSelectAll(codestandard, "stylerule");
                loadSelectAll(naturesenson, "natureseason");
                loadSelectAll(wave, "wave");
                loadSelectAll(band, "band");
                loadSelectAll(brand, "brand");
                loadSelectAll(catezl, "catezl");
                loadSelectAll(catexl, "catexl");
                loadSelectAll(group, "group");
                loadSelectAll(line, "line");
                loadSelectAll(sizeGroup, "sizegroup");
                loadSelectAll(standard, "standard");
                loadSelectAll(devSeasons, "devseason");
                loadSelectAll(uom, "uom");
                loadYear();
                loadPaper();
            }
            else {
                return swal("下拉框数据获取失败，请稍后", "", Error);
            }
        },
        error: function () {
            //程序出现未知错误，请稍候重试
            return swal(ICICLELangUtil.getText("", 1035), "", "error");
        }
    })
}

function getCategoryLevle2(categoryLevel1Code){
    if(productCategoryCode!="P02"){
        var categoryLevel2Code = viewSelectproductinfoList.c3;
        $.ajax({
            url: webroot+"/product/getCategoryLevle2List",
            type : "GET",
            dataType : "json",
            data : {
                "categoryLevel1Code" : categoryLevel1Code,
                "productCategoryCode" : productCategoryCode
            },
            success: function (response) {
                var categoryLevle2List = response.data;
                $("#catexl").html("<option value=''>-- 请选择 --</option>");
                for (i = 0; i < categoryLevle2List.length; i++) {
                    var categoryLevel2 = categoryLevle2List[i];
                    $("#catexl").append("<option value='"+categoryLevel2.itemKey+"'>" + categoryLevel2.itemValue +"</option>");
                }
                $("#catexl").val(categoryLevel2Code).select2();
            },
            error:function () {
                return swal("下拉框数据获取失败，请稍后再试", "", Error);
            }
        });
    }
}
function RenderProductData() {
    if (viewSelectproductinfoList != null && viewSelectproductinfoList.length != 0) {
        var productcode = viewSelectproductinfoList.productCode;
        if(productCategoryCode=="P02"){
            $("#catexlDiv").hide();
        }else{
            $("#catexlDiv").show();
        }
        $("#productcode").val(productcode);

        $("#brand").val(viewSelectproductinfoList.brand).trigger("change");
        $("#uom").val(viewSelectproductinfoList.uom).trigger("change");

        loadCatgroy(categoryLevel1List,viewSelectproductinfoList.c2,'catezl');

        if(productCategoryCode!="P02"){
            loadCatgroy(categoryLevel2List,viewSelectproductinfoList.c3,'catexl');
        }
        if(viewSelectproductinfoList.status==true){
            $("#status").val('1').trigger("change");
        }else{
            $("#status").val('0').trigger("change");
        }
        $("#syncstatus").val(viewSelectproductinfoList.syncStatus).trigger("change");
        var productType = viewSelectproductinfoList.productType;
        $("#type").val(productType);
        var productName = viewSelectproductinfoList.productName;
        $("#pname").val(productName);
        var unitPrice = viewSelectproductinfoList.unitPrice;
        $("#price").val(unitPrice);
        var cateDl = viewSelectproductinfoList.c1;
        $("#catedl").val(cateDl);

        var productDesc = viewSelectproductinfoList.productDesc;
        $("#remarks").val(productDesc);
        var creationDate = viewSelectproductinfoList.creationDate;
        $("#createtime").val(commonClass.date_time_long(new Date(creationDate)));
        var lastUpdateDate = viewSelectproductinfoList.lastUpdateDate;
        if (lastUpdateDate != null) {
            $("#updatetime").val(commonClass.date_time_long(new Date(lastUpdateDate)));
        }
    }

}

function loadCatgroy(selectlist,category,id) {
    $("#" + id).html("");
    for (var i = 0; i < selectlist.length; i++) {
        var content = selectlist[i];
        var html = "<option value='" + content.itemKey + "'>" + content.itemValue + "</option>";
        $("#" + id).append(html);
    }
    $("#" + id).val(category).trigger("change");
}


function loadSelectAll(selectlist, id) {
    for (var i = 0; i < selectlist.length; i++) {
        var content = selectlist[i];
        var html = "<option value='" + content.itemKey + "'>" + content.itemValue + "</option>";
        $("#" + id).append(html);
    }
}

function loadYear() {
    for (var i = 1997; i < new Date().getFullYear() + 10; i++) {
        var html = "<option value='" + i + "'>" + i + "</option>";
        $("#year").append(html);
    }
}

function getAttributeDefinedList(productCategoryCode){
    $.ajax({
        type: "GET",
        url: webroot + "/product/getAttributeDefinedList",
        data: {
            'productCategoryCode': productCategoryCode,
            'productCode': productCode,
        },
        success: function (response) {
            if (response.code == 200) {
                productAttribDefList = response.data;

                if(productAttribDefList.productAttributeDefinedVOList=="" ){
                    $("#attrdetailDiv").hide();
                    $("#smt").hide();
                }else{
                    $("#attrdetailDiv").show();
                    $("#smt").show();
                }
                loadAttribDef();
                loadValue();
            }else {
                return swal("属性明细获取失败，请稍后再试", "", "error");
            }
        },
        error: function () {
            return swal(ICICLELangUtil.getText("", 1035), "", "error");
        }
    })

}

function loadAttribDef() {
    var html = "<div class=\"form-group col-md-12\">";
    for (var i = 0; i < productAttribDefList.productAttributeDefinedVOList.length; i++) {
        var productAttribDef = productAttribDefList.productAttributeDefinedVOList[i];
        html += "<div class=\"form-group col-md-6 \">";
        html += "<label class=\"control-label col-md-4\">" + productAttribDef.defName + "</label>";
        html += "<div class=\"col-md-5\" >";
        html += "<div class=\"input-icon right\">";
        html += "<i class=\"fa\"></i>";

        if(productAttribDef.selectTable!= ""){
            var selectList = productAttribDef.selectList;
            html += "<select id=\"" + productAttribDef.defCode + "\" class=\"form-control\"  data-defid='" + productAttribDef.id + "' >";
            html += "<option value=\"\">-- 请选择 --</option>";
            for (var j = 0; j < selectList.length; j++) {
                var item = selectList[j];
                html += "<option value=\"" + item.itemKey + "\" data-itemcode='"+item.id+"'>" + item.itemValue + "</option>";
            }
            html += "</select>";


        }else {
            if (productAttribDef.hasItem) {
                var itemList = spilValidItem(productAttribDef);
                html += "<select id=\"" + productAttribDef.defCode + "\" class=\"form-control\"  data-defid='" + productAttribDef.id + "' >";
                html += "<option value=\"\">-- 请选择 --</option>";
                for (var j = 0; j < itemList.length; j++) {
                    var item = itemList[j];
                    if (item.status == true) {
                        html += "<option value=\"" + item.id + "\" data-itemcode='" + item.code + "'>" + item.name + "</option>";
                    }

                }
                html += "</select>"

            } else if (productAttribDef.defLength > 0) {
                html += " <textarea type=\"text\" name=\"" + productAttribDef.defType + "\" id=\"" + productAttribDef.defCode + "\" data-defid='" + productAttribDef.id + "' class=\"form-control discountrate\"/></textarea>";
            }
            else {
                html += " <input type=\"text\" name=\"" + productAttribDef.defType + "\" id=\"" + productAttribDef.defCode + "\" data-defid='" + productAttribDef.id + "' class=\"form-control discountrate\"/>";
            }
        }
        html += "</div></div></div>";
    }
    html += "</div>";
    $("#attribute_add").append(html);
}



//找出对应的不同id
function spilValidItem(productAttribDef) {
    var resultList = productAttribDefList.productAttributeItemsVOList;
    var itemList = $.grep(resultList, function (value) {
        return value.defId == productAttribDef.id;
    });
    return itemList;
}


function loadValue() {
    for (var i = 0; i < productAttribDefList.productAttributeDefinedVOList.length; i++) {
        var productAttribDef = productAttribDefList.productAttributeDefinedVOList[i];
        var defCode = $("#" + $.trim(productAttribDef.defCode));
        if (productAttribDef.hasItem) {
            var value = productAttribDef.value;
            defCode.val(value);
        }else{
            defCode.val(productAttribDef.value);
        }
    }
}

