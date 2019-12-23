var FormValidation = function () {
    // validation using icons
    var handleValidationRegister = function () {
        // for more info visit the official plugin documentation:
        // http://docs.jquery.com/Plugins/Validation
        var formreg = $(".form_completejob");
        var errorreg = $('.alert-danger', formreg);
        var successreg = $('.alert-success', formreg);
        formreg.validate({
            onfocusout: function(element) { $(element).valid(); },
            onclick: function(element) { $(element).valid(); },
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",  // validate all fields including form hidden input
            rules: {
                // buyerid: {
                //     required: true,
                //     validId: true,
                //     maxlength: 10
                // },
                buyername: {
                    required: true
                },
                buyshortname: {
                    required: true
                },
                fullname: {
                    required: true
                },
                spellfirst: {
                    spellFirst: true,
                    required: true
                },
                address: {
                    required: true
                },
                type: {
                    required: true
                },
                state: {
                    required: true
                },
                connectphone: {
                    required: true
                },
                phone: {
                    phoneCN: true
                },
                joinfee: {
                    required: true,
                    number: true
                },
                startime: {
                    required: true,
                    date: true
                },
                endtime: {
                    required: true,
                    date: true
                },
                cityId: {
                    required: true
                },
                manageCenter: {
                    required: true
                },
                email: {
                    email: true
                },
                mobile: {
                    mobilephoneCN: true
                },
                fax: {
                    validFax: true
                }

            },

            invalidHandler: function (event, validator) { //display error alert on form submit
                successreg.hide();
                errorreg.show();
                App.scrollTo(errorreg, -200);
            },

            errorPlacement: function (error, element) { // render error placement for each input type
                var icon = $(element).parent('.input-icon').children('i');
                icon.removeClass('fa-check').addClass("fa-warning");
                icon.attr("data-original-title", error.text()).tooltip({'container': 'body'});
            },

            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').removeClass("has-success").addClass('has-error'); // set error class to the control group
            },

            unhighlight: function (element) { // revert the change done by hightlight

            },

            success: function (label, element) {
                var icon = $(element).parent('.input-icon').children('i');
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
                icon.removeClass("fa-warning").addClass("fa-check");
            },

            submitHandler: function (form) {
                successreg.show();
                errorreg.hide();
            }
        });
    }
    return {
        init: function () {
            handleValidationRegister();
        }
    };
}();
var defValidation = function () {
    // validation using icons
    var handleValidationRegister = function () {
        // for more info visit the official plugin documentation:
        // http://docs.jquery.com/Plugins/Validation
        var formreg = $(".def_completejob");
        var errorreg = $('.def_danger', formreg);
        var successreg = $('.def_success', formreg);
        formreg.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",  // validate all fields including form hidden input
            rules: {
                Decimal: {
                    number: true
                },
                Float: {
                    number: true
                },
                Datetime: {
                    date: true
                },
                Int:{
                    digits:true
                },
                sycntytle:{
                    required:true
                }


            },

            invalidHandler: function (event, validator) { //display error alert on form submit
                successreg.hide();
                errorreg.show();
                App.scrollTo(errorreg, -200);
            },

            errorPlacement: function (error, element) { // render error placement for each input type
                var icon = $(element).parent('.input-icon').children('i');
                icon.removeClass('fa-check').addClass("fa-warning");
                icon.attr("data-original-title", error.text()).tooltip({'container': 'body'});
            },

            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').removeClass("has-success").addClass('has-error'); // set error class to the control group
            },

            unhighlight: function (element) { // revert the change done by hightlight

            },

            success: function (label, element) {
                var icon = $(element).parent('.input-icon').children('i');
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
                icon.removeClass("fa-warning").addClass("fa-check");
            },

            submitHandler: function (form) {
                successreg.show();
                errorreg.hide();
            }
        });
    }
    return {
        init: function () {
            handleValidationRegister();
        }
    };
}();
var itemlist = [];
var list;
var buyerlist = [];
var attributelist = [];
var buyerAttribDefList = [];
var cityVOList = [];
var zoneVOList = [];
var buyerTypeVOList = [];
var managerList = [];
var manageCenterList = [];
var buyerNo = commonClass.getQueryString("id");
$(function () {
    commonClass.initDatePicker();
    $(".select2-multiple").select2();
    if (jQuery.validator) {
        //手机号码格式错误
        jQuery.validator.addMethod("mobilephoneCN", function (value, element) {
            return this.optional(element) || commonClass.checkMobilePhone(value);
        }, ICICLELangUtil.getText("", 1050));
        //电话号码格式错误
        jQuery.validator.addMethod("phoneCN", function (value, element) {
            return this.optional(element) || commonClass.checkPhoneCN(value);
        }, ICICLELangUtil.getText("", 1052));
        //拼音首字母格式错误
        jQuery.validator.addMethod("spellFirst", function (value, element) {
            return this.optional(element) || commonClass.checkSpellFirst(value);
        }, ICICLELangUtil.getText("", 1186));
        //id格式错误
        jQuery.validator.addMethod("validId", function (value, element) {
            return this.optional(element) || commonClass.checkId(value);
        }, ICICLELangUtil.getText("", 1308));
        //传真格式错误
        jQuery.validator.addMethod("validFax", function (value, element) {
            return this.optional(element) || commonClass.checkFax(value);
        }, ICICLELangUtil.getText("", 1187));
        $.validator.prototype.elements = function () {
            var validator = this,
                rulesCache = {};
            return $(this.currentForm)
                .find("input, select, textarea")
                .not(":submit, :reset, :image, [disabled]")
                .not(this.settings.ignore)
                .filter(function () {
                    if (!this.name && validator.settings.debug && window.console) {
                        console.error("%o has no name assigned", this);
                    }
                    rulesCache[this.name] = true;
                    return true;
                });
        }
    }
    //加载页面属性
    $("#state_1").addClass("active");

    $("#endtime").change(function () {
        $("#endtime").valid();
    });
    $("#startime").change(function () {
        $("#startime").valid();
    });
    $("#cityId").change(function () {
        $("#cityId").valid();
    });
    $("#manageCenter").change(function () {
        $("#manageCenter").valid();
    });
    //加载define
    $("#loading").modal('show');
    $.ajax({
        url: webroot + "/buyer/buyerattribdefined",
        type: "POST",
        success: function (response) {
            if (response.code == 200) {
                buyerAttribDefList = response.data;
                loadAttribDef();
                if (buyerNo != null) {
                    $("#buyerTitle").html("编辑代理商")
                    loadAllData(buyerNo);
                }
                else {
                    $("#buyerTitle").html("新增代理商")
                }
                $("#loading").modal('hide');
            } else {
                //数据获取为空，请稍后重试
                swal(ICICLELangUtil.getText("", 1049), "", "warning");
                $("#loading").modal('hide');
            }
        },
        error: function (response) {
            //数据获取为空，请稍后重试
            swal(ICICLELangUtil.getText("", 1049), "", "warning");
            $("#loading").modal('hide');
        }
    });
    //加载所有的下拉列表框
    $.ajax({
        url: webroot + "/buyer/getdropdownlist",
        type: "POST",
        success: function (response) {
            if (response.code == 200) {
                cityVOList = response.data.cityVOList;
                zoneVOList = response.data.zoneVOList;
                buyerTypeVOList = response.data.buyerTypeVOList;
                manageCenterList = response.data.manageCenterList;
                loadCityList();
                loadBuyerTypeList();
                loadManageCenterList();
            } else {
                swal("下拉款数据拉取失败", "", "warning");
            }
        },
        error: function (response) {
            swal("下拉款数据拉取失败", "", "warning");
        }
    });
    FormValidation.init();
    defValidation.init();
    $("#stepthree").hide();
    $("#nextsyn").hide();
    $("#next").click(function () {//下一步按钮
        if ($(".form_completejob").valid()&&$(".def_completejob").valid()) {
            var buyerPattern = $("#buyer_pattern option:selected").attr("data-itemcode");
            var zone = $("#zone").val();
            if(buyerPattern=="2" && (zone=="" || zone==null)){
                return swal("请选择所属分区", "", "warning");
            }
            if (buyerNo == null || typeof(buyerNo) == "undefined") {//新增
                var buyerid = $("#buyerid").val();
                if (buyerid != "") {
                    var regExp = /^([0-9]|[a-zA-Z]){6}$/;
                    if (!regExp.test(buyerid)) {
                        return swal("客户ID格式不正确，必须为6位数字或字母", "", "warning");
                    } else {
                        $.ajax({
                            type: "GET",
                            contentType: "application/json;charset=utf-8",
                            url: webroot + "/buyer/checkBuyerId",
                            data: {
                                "buyerId": buyerid
                            },
                            success: function (response) {
                                if (response.code != 200) {
                                    return swal("客户ID已存在，请重新输入", "", "warning");
                                } else {
                                    $("#stepsecond").hide();
                                    $("#stepfirst").hide();
                                    $("#stepthree").show();
                                    $("#nextsyn").show();
                                }
                            }
                        });
                    }
                } else {
                    $("#stepsecond").hide();
                    $("#stepfirst").hide();
                    $("#stepthree").show();
                    $("#nextsyn").show();
                }

            } else {
                $("#stepsecond").hide();
                $("#stepfirst").hide();
                $("#stepthree").show();
                $("#nextsyn").show();
            }
        }
    })
    $("#btnsave").click(function () {
        var flag = $('#buyerValue').val();
        if ($(".form_completejob").valid()&&$(".def_completejob").valid()) {
            var buyerid = commonClass.replaceAllQuotationMarks($('#buyerid').val());
            var buyername = commonClass.replaceAllQuotationMarks($('#buyername').val());
            var buyshortname = commonClass.replaceAllQuotationMarks($('#buyershotname').val());
            var fullname = commonClass.replaceAllQuotationMarks($('#fullname').val());
            var spellfirst = commonClass.replaceAllQuotationMarks($('#spellfirst').val());
            var address = commonClass.replaceAllQuotationMarks($('#address').val());
            var type = $('#type').val();
            var state = $('#state .active input').val();
            var connectpeople = commonClass.replaceAllQuotationMarks($('#connectpeople').val());
            var phone = commonClass.replaceAllQuotationMarks($('#phone').val());
            var ycode = $('#ycode').val();
            var mobile = commonClass.replaceAllQuotationMarks($('#mobile').val());
            var email = $('#email').val();
            var fax = commonClass.replaceAllQuotationMarks($('#fax').val());
            var remarks = commonClass.replaceAllQuotationMarks($('#remarks').val());
            var startime = commonClass.replaceAllQuotationMarks($('#startime').val());
            var endtime = commonClass.replaceAllQuotationMarks($('#endtime').val());
            var joinfee = commonClass.replaceAllQuotationMarks($('#joinfee').val());
            var legalPerson = commonClass.replaceAllQuotationMarks($('#legalPerson').val());
            var companyName = commonClass.replaceAllQuotationMarks($('#companyName').val());
            var bankName = commonClass.replaceAllQuotationMarks($('#bankName').val());
            var bankAccount = commonClass.replaceAllQuotationMarks($('#bankAccount').val());
            var taxNumber = commonClass.replaceAllQuotationMarks($('#taxNumber').val());
            var zone = commonClass.replaceAllQuotationMarks($('#zone').val());
            var buyerAddress = commonClass.replaceAllQuotationMarks($('#buyerAddress').val());
            var manageCenterId = $("#manageCenter").val();

            // var money = commonClass.replaceAllQuotationMarks($('#money').val());
            var cityid = $('#cityId').select2("val");
            //第二部分
            var itemlist = [];
            for (var i = 0; i < buyerAttribDefList.buyerAttribDefVOList.length; i++) {
                var buyerAttribDef = buyerAttribDefList.buyerAttribDefVOList[i];
                var code = $("#" + $.trim(buyerAttribDef.buyerAttribDefCode)).val();
                var itemcode=$("#" + $.trim(buyerAttribDef.buyerAttribDefCode)).find('option:selected').data('itemcode');
                if (buyerAttribDef.hasItem) {
                    if(code == ""){
                        itemlist.push({
                            attribDefId: buyerAttribDef.buyerAttribDefId,
                            attribItemId: code,
                            buyerAttriValue: ""
                        });
                    }else{
                        var itemList = spilValidItem(buyerAttribDef);
                        for (var j = 0; j < itemList.length; j++) {
                            var item = itemList[j];
                            if (item.buyerAttribItemId.length != 0 && (item.buyerAttribItemId == code)) {
                                itemlist.push({
                                    attribDefId: buyerAttribDef.buyerAttribDefId,
                                    attribItemId: code,
                                    buyerAttriValue: itemcode
                                });
                            }
                        }
                    }
                }
                else {
                    if (code != 0) {
                        itemlist.push({
                            attribDefId: buyerAttribDef.buyerAttribDefId,
                            attribItemId: 0,
                            buyerAttriValue: commonClass.replaceAllQuotationMarks(code)
                        });
                    }
                }
            }
            var reslist = $("#sel_menu2").val();
            var requestType = "";
            var requestUrl = "";
            var data = {};
            if (buyerNo == null || typeof(buyerNo) == "undefined") {//新增
                data = {
                    "buyerId": buyerid,
                    "buyerTypeId": type,
                    "buyerFullName": fullname,
                    "buyerName": buyername,
                    "buyerShortName": buyshortname,
                    "buyerSpell": spellfirst,
                    "buyerContact": connectpeople,
                    "buyerState": state,
                    "buyerPhone": phone,
                    "buyerMobile": mobile,
                    "buyerEmail": email,
                    "buyerFax": fax,
                    "buyerJoinningFee": joinfee,
                    "buyerDesc": remarks,
                    "buyerStartDate": startime,
                    "buyerEndDate": endtime,
                    "cityId": cityid,
                    "attr": itemlist,
                    "syn": reslist,
                    "legalPerson": legalPerson,
                    "companyName": companyName,
                    "bankName": bankName,
                    "bankAccount": bankAccount,
                    "taxNumber": taxNumber,
                    "zoneId": zone,
                    "manageCenterId": manageCenterId,
                    "buyerAddress": buyerAddress

                };
                requestType = "POST";
                requestUrl = webroot + "/buyer/insertbuyerdetail";
            }
            else {
                data = {
                    "buyerId": buyerid,
                    "buyerNo": buyerNo,
                    "buyerTypeId": type,
                    "buyerFullName": fullname,
                    "buyerName": buyername,
                    "buyerShortName": buyshortname,
                    "buyerSpell": spellfirst,
                    "buyerContact": connectpeople,
                    "buyerState": state,
                    "buyerPhone": phone,
                    "buyerMobile": mobile,
                    "buyerEmail": email,
                    "buyerFax": fax,
                    "buyerJoinningFee": joinfee,
                    "buyerDesc": remarks,
                    "buyerStartDate": startime,
                    "buyerEndDate": endtime,
                    "cityId": cityid,
                    "attr": itemlist,
                    "syn": reslist,
                    "legalPerson": legalPerson,
                    "companyName": companyName,
                    "bankName": bankName,
                    "bankAccount": bankAccount,
                    "taxNumber": taxNumber,
                    "zoneId": zone,
                    "manageCenterId": manageCenterId,
                    "buyerAddress": buyerAddress
                };
                requestType = "PUT";
                requestUrl = webroot + "/buyer/updatebuyerdetail";
            }
            $.ajax({
                type: requestType,
                url: requestUrl,
                contentType: "application/json;charset=utf-8",
                data: JSON.stringify(data),
                success: function (response) {
                    if (response.code == 200) {
                        var message = response.data[1];
                        if(message.indexOf("失败")!=-1){
                            swal({
                                    //确定--失败
                                    title: response.data[1],
                                    text: "",
                                    type: "error",
                                    showCancelButton: false,
                                    confirmButtonClass: "btn-success",
                                    confirmButtonText: ICICLELangUtil.getText("", 1007),
                                    closeOnConfirm: true
                                },
                                function () {
                                    location.href = webroot + "/buyer/buyerinfo?id=" + response.data[0];
                                });
                        }else{
                            swal({
                                    //确定--成功
                                    title: response.data[1],
                                    text: "",
                                    type: "success",
                                    showCancelButton: false,
                                    confirmButtonClass: "btn-success",
                                    confirmButtonText: ICICLELangUtil.getText("", 1007),
                                    closeOnConfirm: true
                                },
                                function () {
                                    location.href = webroot + "/buyer/buyerinfo?id=" + response.data[0];
                                });
                        }

                    }
                    else {
                        swal(response.message, "", "error");
                    }
                },
                beforeSend: function (XMLHttpRequest) {
                    $("#btnsave").attr("disabled", "disabled");
                    $('#loading').modal('show');
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $("#btnsave").removeAttr("disabled");
                    $('#loading').modal('hide');
                },
                error: function () {
                    //程序出现未知错误，请稍候重试
                    swal(ICICLELangUtil.getText("", 1035), "", "error");
                }
            })

        }
    })
    $("#again").click(function () {
        $("#stepfirst").show();
        $("#stepsecond").show();
        $("#stepthree").hide();
        $("#nextsyn").hide();
    });
    $("#sel_menu2").select2({
        tags: true,
    });
});

function loadAllData(buyerNo) {
    $.ajax({
        type: "POST",
        contentType: "application/json;charset=utf-8",
        url: webroot + "/buyer/findbylistid?buyerNo=" + buyerNo,
        success: function (response) {
            if (response.code == 200) {
                attributelist = response.data.attr;
                buyerlist = response.data;
                RenderData();
                loadValue();
                loadManageCenterList();
            }
        }
    });
}

function RenderAttribute(viewBuyerItemDefine) {
    var html = "";
    for (var i = 0; i < viewBuyerItemDefine.length; i++) {
        html += " <div class=\"form-group col-md-6\"> <label class=\"control-label col-md-4\">" + viewBuyerItemDefine[i].buyerAttribDefName + " </label>";
        html += " <div class=\"col-md-5\" about=\"\">";
        html += " <div class=\"input-icon right\">";
        html += " <i class=\"fa\"></i>";
        if(viewBuyerItemDefine)
            html += " <input type=\"text\" name=\"discountrate\"  data-itemid=\"" + viewBuyerItemDefine[i].buyerAttribItemId + "\"  data-defid=\"" + viewBuyerItemDefine[i].buyerAttribDefId + "\"  class=\"form-control discountrate\"";
        html += "  placeholder=\"\"/>";
        html += " </div>";
        html += " </div>";
        html += "</div>";
    }
    $('#attribute_add').html(html);
    html = "";
}

function RenderData() {
    console.log('managecenterid:'+buyerlist.manageCenterId);
    $('#buyerid').val(buyerlist.buyerId);
    $('#buyerid').attr("disabled", "disabled");
    $('#buyername').val(buyerlist.buyerName);
    $('#legalPerson').val(buyerlist.legalPerson);
    $('#companyName').val(buyerlist.companyName);
    $('#bankName').val(buyerlist.buyerAccountVO==null?"":buyerlist.buyerAccountVO.bankName);
    $('#bankAccount').val(buyerlist.buyerAccountVO==null?"":buyerlist.buyerAccountVO.bankAccount);
    $('#taxNumber').val(buyerlist.taxNumber);
    // $('#zone').val(buyerlist.zoneId);
    $('#buyerAddress').val(buyerlist.buyerAddress);
    $('#buyershotname').val(buyerlist.buyerShortName);
    $('#fullname').val(buyerlist.buyerFullName);
    $('#spellfirst').val(buyerlist.buyerSpell);
    var typeid = buyerlist.buyerTypeId;
    $('#type').val(buyerlist.buyerTypeId);
    var state = buyerlist.buyerState;
    $("#state_0").removeClass("active");
    $("#state_1").removeClass("active");
    $("#state_" + state).addClass("active");
    $("#state_" + state).attr("checked", "true");
    $('#connectpeople').val(buyerlist.buyerContact);
    $('#phone').val(buyerlist.buyerPhone);
    $('#ycode').val(buyerlist.buyerId);
    $('#mobile').val(buyerlist.buyerMobile);
    $('#email').val(buyerlist.buyerEmail);
    $('#fax').val(buyerlist.buyerFax);

    $('#manageCenter').val(buyerlist.manageCenterId).select2();
    $('#zone').val(buyerlist.zoneId).select2();

    $('#remarks').val(buyerlist.buyerDesc);
    if (buyerlist.buyerStartDate != null && buyerlist.buyerStartDate != 0) {
        $('#startime').datepicker("setDate", new Date(buyerlist.buyerStartDate));
    }
    if (buyerlist.buyerEndDate != null && buyerlist.buyerEndDate != 0) {
        $('#endtime').datepicker("setDate", new Date(buyerlist.buyerEndDate));
    }
    $('#joinfee').val(buyerlist.buyerJoinningFee);
    $('#cityId').val(buyerlist.cityId).select2();
}

function loadAttribDef() {
    var html = "<div class=\"form-group col-md-12\">";

    for (var i = 0; i < buyerAttribDefList.buyerAttribDefVOList.length; i++) {
        var buyerAttribDef = buyerAttribDefList.buyerAttribDefVOList[i];
        html += "<div class=\"form-group col-md-6 \">";
        html += "<label class=\"control-label col-md-4\">" + buyerAttribDef.buyerAttribDefName + "</label>";
        html += "<div class=\"col-md-5\" >";
        html += "<div class=\"input-icon right\">";
        html += "<i class=\"fa\"></i>";
        if (buyerAttribDef.hasItem) {
            var itemList = spilValidItem(buyerAttribDef);
            html += "<select id=\"" + buyerAttribDef.buyerAttribDefCode + "\" class=\"form-control\"  data-defid='" + buyerAttribDef.buyerAttribDefId + "' >";
            html += "<option value=\"\">-- 请选择 --</option>";
            for (var j = 0; j < itemList.length; j++) {
                var item = itemList[j];
                if (item.status == 1) {
                    html += "<option value=\"" + item.buyerAttribItemId + "\" data-itemcode='"+item.buyerAttribItemCode+"'>" + item.buyerAttribItemName + "</option>";
                }

            }
            html += "</select>"

        } else if(buyerAttribDef.itemLength>0){
            html += " <textarea type=\"text\" name=\""+buyerAttribDef.itemType+"\" id=\"" + buyerAttribDef.buyerAttribDefCode + "\" data-defid='" + buyerAttribDef.buyerAttribDefId + "' class=\"form-control discountrate\"/></textarea>";
        }
        else {
            html += " <input type=\"text\" name=\""+buyerAttribDef.itemType+"\" id=\"" + buyerAttribDef.buyerAttribDefCode + "\" data-defid='" + buyerAttribDef.buyerAttribDefId + "' class=\"form-control discountrate\"/>";
        }
        html += "</div></div></div>";
        if(buyerAttribDef.sycn){
            $("#" + $.trim(buyerAttribDef.buyerAttribDefCode)).attr("name","sycntytle");
        }
        $("#" + $.trim(buyerAttribDef.buyerAttribDefCode)).data("attributeId", 0);
    }
    html += "</div>";
    $("#attribute_add").append(html);
}



function loadValue() {
    for (var i = 0; i < buyerAttribDefList.buyerAttribDefVOList.length; i++) {
        var buyerAttribDef = buyerAttribDefList.buyerAttribDefVOList[i];
        var defCode = $("#" + $.trim(buyerAttribDef.buyerAttribDefCode));
        var defid = defCode.data("defid");
        for (var j = 0; j < attributelist.length; j++) {
            if (attributelist[j].attribDefId == defid) {
                if (buyerAttribDef.hasItem) {
                    defCode.val(attributelist[j].attribItemId);
                }
                else {
                    defCode.val(attributelist[j].buyerAttriValue);
                }
            }
        }
    }


}


function loadBuyerTypeList() {
    var typeid = buyerlist.buyerTypeId;
    for (var i = 0; i < buyerTypeVOList.length; i++) {
        var buyertype = buyerTypeVOList[i];
        var html = "<option value='" + buyertype.buyerTypeId + "' id=\"" + buyertype.buyerTypeName + "\">" + buyertype.buyerTypeName + "</option>";
        $("#type").append(html);
        if (buyertype.buyerTypeId == typeid) {
            $("#type").val(buyertype.buyerTypeId);
        }
    }


}

//找出对应的不同id
function spilValidItem(buyerAttribDef) {
    var resultList = buyerAttribDefList.buyerAttribItemList;
    var itemList = $.grep(resultList, function (value) {
        return value.buyerAttribDefId == buyerAttribDef.buyerAttribDefId;
    });
    return itemList;
}

function checkBuyerId(value){
    if(value!=""){
        var regExp = /^([0-9]|[a-zA-Z]){6}$/;
        if(!regExp.test(value)){
            return swal("客户ID格式不正确，必须为6位数字或字母", "", "warning");
        }else{
            $.ajax({
                type: "GET",
                contentType: "application/json;charset=utf-8",
                url: webroot + "/buyer/checkBuyerId",
                data: {
                    "buyerId":value
                },
                success: function (response) {
                    if (response.code != 200) {
                        return swal("客户ID已存在，请重新输入", "", "warning");
                    }
                }
            });
        }
    }
}

function loadCityList() {
    var cityid = buyerlist.cityId;
    for (var i = 0; i < cityVOList.length; i++) {
        var city = cityVOList[i];
        var html = "<option class='get-class' value='" + city.cityId + "'>" + city.cityName + "</option>";
        $("#city").append(html);
        if (city.cityId == cityid) {
            $('#cityId').val(city.cityId).select2();
        }
    }
}

function loadManageCenterList() {
    var managecenter = buyerlist.manageCenterId;
    for (var i = 0; i < manageCenterList.length; i++) {
        var manageCenter = manageCenterList[i];
        var html = "<option value='" + manageCenter.managerCenterId + "'>" + manageCenter.managerCenterName + "</option>";
        $("#manageCenterId").append(html);
        if (manageCenter.managerCenterId == managecenter) {
            $('#manageCenter').val(manageCenter.managerCenterId).select2();
        }
    }
    if(managecenter!=undefined){
        getZoneList(managecenter);
    }
}


function getZoneList(manageCenterId){
    var zoneId = buyerlist.zoneId;
    $.ajax({
        url: webroot+"/buyer/getZoneList",
        type : "GET",
        dataType : "json",
        data : {
            "manageCenterId" : manageCenterId
        },
        success: function (response) {
            var zoneList = response.data;
            document.getElementById("zone").options.length = 0;
            $("#zone").append("<option value=''>-- 请选择 --</option>");
            for (i = 0; i < zoneList.length; i++) {
                var zone = zoneList[i];
                $("#zone").append("<option value='"+zone.zoneId+"'>" + zone.zoneName +"</option>");
            }
            if(zoneId!=undefined && zoneId!=0){
                $('#zone').val(zoneId).select2();
            }

        },
        error:function () {
            return swal("下拉框数据获取失败，请稍后再试", "", Error);
        }
    });
}

function checkManageCenter(){
    var managecenter = $("#manageCenter").val();
    if(managecenter==""){
        return swal("请先选择现场管理中心", "", "warning");
    }
}
