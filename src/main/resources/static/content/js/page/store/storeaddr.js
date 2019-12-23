var FormValidation = function () {
    var handleValidationRegister = function () {
        var formreg = $(".form_completejob");
        var errorreg = $('.alert-danger', formreg);
        var successreg = $('.alert-success', formreg);
        formreg.validate({
            onfocusout: function(element) { $(element).valid(); },
            onclick: function(element) { $(element).valid(); },
            errorElement: 'span',
            errorClass: 'help-block help-block-error',
            focusInvalid: false,
            ignore: "",
            rules: {
                mobilePhone: {
                    mobilephoneCN: true
                }
                // ,
                // phone: {
                //     phoneCN: true
                // }

            },

            invalidHandler: function (event, validator) {
                successreg.hide();
                errorreg.show();
                App.scrollTo(errorreg, -200);
            },

            errorPlacement: function (error, element) {
                var icon = $(element).parent('.input-icon').children('i');
                icon.removeClass('fa-check').addClass("fa-warning");
                icon.attr("data-original-title", error.text()).tooltip({'container': 'body'});
            },

            highlight: function (element) {
                $(element)
                    .closest('.form-group').removeClass("has-success").addClass('has-error');
            },

            unhighlight: function (element) {

            },

            success: function (label, element) {
                var icon = $(element).parent('.input-icon').children('i');
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
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




var viewStoreaddrList = [];
function initStoreAddrList(){
    $.ajax({
        url: webroot+"/store/findAddrListByStoreNo",
        type: "GET",
        dataType:"json",
        data:{
            "id": commonClass.getQueryString("id")
        },
        success: function (response) {
            if (response.code == 200) {
                viewStoreaddrList = response.data;
                var len = viewStoreaddrList.length;
                if(len>0){
                    var addrHtml = "";
                    for(var i=0;i<len;i++){
                        var storeaddr = viewStoreaddrList[i];
                        var addressState = storeaddr.addressState=="1"?"有效":"无效";
                        var isDefault = storeaddr.isDefault==true?"是":"否";
                        var addressId = storeaddr.addressId;
                        var storeNo = storeaddr.storeNo;
                        var contact = storeaddr.contact;
                        var phone = storeaddr.phone;
                        var mobilePhone = storeaddr.mobilePhone;
                        var addressType = storeaddr.addressType;
                        var addressTypeName = storeaddr.addressTypeName;
                        var address = storeaddr.address.length>40?storeaddr.address.substring(0,40)+"...":storeaddr.address;
                        var title = "地址"+(i+1);
                        var addressAttrs = addressId+","+storeNo+","+addressType+","+storeaddr.isDefault+","+contact+","+phone+","+mobilePhone+","+addressType+","+storeaddr.address+","+storeaddr.addressState;
                        if((i+1)%2==1){//开
                            addrHtml+= "<div class=\"row col-md-12\">";
                        }
                        addrHtml+=
                            "                <div class=\"form-group col-md-6\" style=\"margin-bottom: 20px\">\n" +
                            "                    <div class=\"portlet yellow-crusta box\">";

                        if(addressType=='1'){
                            addrHtml+= "<div class=\"portlet-title\" style='background-color:#f3c200'>";
                        }else if(addressType=='2'){
                            addrHtml+= "<div class=\"portlet-title\" style='background-color:#BF3EBE'>";
                        }else if(addressType=='3'){
                            addrHtml+= "<div class=\"portlet-title\" style='background-color:#1BBC9B'>";
                        }
                        addrHtml+=
                            "                            <div class=\"caption\"><i class=\"fa fa-cogs\"></i>"+title+"</div>\n" +
                            "                            <div class=\"actions\">\n" +
                            "                                <a href=\"javascript:;\" class=\"btn btn-sm\">\n" +
                            "                                    <i class='fa fa-pencil' style='color: darkslategray;'></i> <a href='javascript:void(0)' onclick='showAddrInfo(\""+addressAttrs+"\")' style='cursor: pointer;color:darkslategray;text-decoration:none;' >修改</a>&nbsp;&nbsp;&nbsp;" +
                            "                                    <i class=\"fa fa-remove\" style='color: red;'></i> <a href='javascript:void(0)' data-entiy='" + addressId + "' onclick='deleteAddr(this)' style='cursor: pointer;color:red;text-decoration:none;' >删除</a>&nbsp;&nbsp;&nbsp;" +
                            "                                    <i class=\"fa fa-expeditedssl\" style='color: green;' ></i> <a href='javascript:void(0)'  onclick='setAddrDefault("+addressId+","+addressType+","+viewStoreaddrList[i].isDefault+")' style='cursor: pointer;color:green;text-decoration:none;' >设为默认</a> " +
                            "                                </a>\n" +
                            "                            </div>\n" +
                            "                        </div>\n" +
                            "                        <div class=\"portlet-body\">\n" +
                            "                            <div class=\"row static-info\">\n" +
                            "                                <div class=\"col-md-3 name\"> 地址类型: </div>\n" +
                            "                                <div class=\"col-md-3 value\" style='font-size: 15px;'> "+addressTypeName+"</div>\n" +
                            "                            </div>\n" +
                            "                            <div class=\"row static-info\">\n" +
                            "                                <div class=\"col-md-3 name\"> 联系人: </div>\n" +
                            "                                <div class=\"col-md-3 value\" style='font-size: 15px;'>"+contact+"</div>\n" +
                            "                            </div>\n" +
                            "                            <div class=\"row static-info\">\n" +
                            "                                <div class=\"col-md-3 name\"> 办公电话: </div>\n" +
                            "                                <div class=\"col-md-3 value\" style='font-size: 15px;'> "+phone+"</div>\n" +
                            "                            </div>\n" +
                            "                            <div class=\"row static-info\">\n" +
                            "                                <div class=\"col-md-3 name\"> 手机号码: </div>\n" +
                            "                                <div class=\"col-md-3 value\" style='font-size: 15px;'> "+mobilePhone+"</div>\n" +
                            "                            </div>\n" +
                            "                            <div class=\"row static-info\">\n" +
                            "                                <div class=\"col-md-3 name\"> 详细地址: </div>\n" +
                            "                                <div class=\"col-md-9 value\" style='font-size: 15px;' title='"+storeaddr.address+"'> "+address+"</div>\n" +
                            "                            </div>\n" +
                            "                            <div class=\"row static-info\">\n" +
                            "                                <div class=\"col-md-3 name\"> 地址状态: </div>\n" +
                            "                                <div class=\"col-md-3 value\" style='font-size: 15px;'> "+addressState+"</div>\n" +
                            "                            </div>\n" +
                            "                            <div class=\"row static-info\">\n" +
                            "                                <div class=\"col-md-3 name\"> 是否默认: </div>\n" +
                            "                                <div class=\"col-md-3 value\" style='font-size: 15px;'> "+isDefault+"</div>\n" +
                            "                            </div>\n" +
                            "                        </div>\n" +
                            "                    </div>\n" +
                            "            </div>";
                        if((i+1)%2==0 || (i+1) == len){//闭
                            addrHtml+= "</div>";
                        }
                    }
                    $('#savedAddr').append(addrHtml);
                }
            }
            else {
                swal("失败", response.message, "error");
            }
        }
    });
}

//地址删除功能
function deleteAddr(curr) {
    var addressId = $(curr).data("entiy");
    swal({
            title: "确认删除此地址信息吗?",
            text: "该地址信息将不会显示在您的页面中，请谨慎处理！",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-success",
            confirmButtonText: "删除",
            cancelButtonText: "取消",
            closeOnConfirm: false
        },
        function () {
            $.ajax({
                type: "DELETE",
                url: webroot + "/store/deleteaddr/" + addressId,
                success: function (data) {
                    if (data.code == 200) {
                        swal({
                                title: "成功删除该地址，页面上将不再显示！",
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
                    } else {
                        swal(data.message, "", "warning");
                    }
                },
                beforeSend: function (XMLHttpRequest) {
                    $('#loading').modal('show');
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $('#loading').modal('hide');
                },
                error: function () {
                    //程序出现未知错误，请稍候重试
                    swal(ICICLELangUtil.getText("", 1035), "", "error");
                }

            })
        });
}

//地址设为默认功能
function setAddrDefault(addressId,addressType,isDefault) {
    var item = {
        "addressId": addressId,
        "addressType": addressType
    }
    if(isDefault == "1"){
        swal({
            title: "当前地址已经是默认地址！",
            text: "",
            type: "warning",
            showCancelButton: false,
            confirmButtonClass: "btn-success",
            confirmButtonText: "确定",
            closeOnConfirm: true
        });
    }else{
        $.ajax({
            type: "PUT",
            url: webroot + "/store/setAddrDefault",
            dataType:"json",
            data: JSON.stringify(item),
            success: function (data) {
                if (data.code == 200) {
                    swal({
                            title: "默认地址设置成功！",
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
                } else {
                    swal({
                        title: data.message,
                        text: "",
                        type: "warning",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: "确定",
                        closeOnConfirm: true
                    });
                }
            },
            complete: function (XMLHttpRequest, textStatus) {
                $('#loading').modal('hide');
            },
            error: function () {
                //程序出现未知错误，请稍候重试
                swal(ICICLELangUtil.getText("", 1035), "", "error");
            }

        });
    }

}

function showAddrInfo(addressParams) {
    $('#addrAdressType').html("");
    var addressArrays = addressParams.split(",");
    $("#storeAddrUpdateDiv").modal('show');
    $('#addrContact').val(addressArrays[4]);
    $('#addrPhone').val(addressArrays[5]);
    $('#addrMobilePhone').val(addressArrays[6]);
    $('#addrAddress').val(addressArrays[8]);
    var status = addressArrays[9];
    $('#addressStateDiv').children('label').removeClass("active");
    $($('#addressStateDiv').children('label')[status]).addClass("active");
    $("input[name=addressState][value='"+status+"']").prop("checked", "checked");
    var isDefault = addressArrays[3]=="true"?"1":"0";
    $('#addrIsDefaultDiv').children('label').removeClass("active");
    $($('#addrIsDefaultDiv').children('label')[isDefault]).addClass("active");
    $("input[name=addrIsDefault][value='"+isDefault+"']").prop("checked", "checked");
    $("#storeAddrId").val(addressArrays[0]);
    loadStoreAddrLists(addressArrays[2]);

}


$(function(){

    if (jQuery.validator) {
        //手机号码格式错误
        jQuery.validator.addMethod("mobilephoneCN", function (value, element) {
            return this.optional(element) || commonClass.checkMobilePhone(value);
        }, ICICLELangUtil.getText("", 1050));
        //电话号码格式错误
        jQuery.validator.addMethod("phoneCN", function (value, element) {
            return this.optional(element) || commonClass.checkPhoneCN(value);
        }, ICICLELangUtil.getText("", 1052));
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

    FormValidation.init();
    //加载地址类型列表
    loadStoreAddrList("addressType");
    //加载已保存的地址
    initStoreAddrList();
    //保存地址
    $("#addrsave").click(function () {
        var addressType = commonClass.replaceAllQuotationMarks($("#addressType").val());
        var contact = commonClass.replaceAllQuotationMarks($('#contact').val());
        var phone = commonClass.replaceAllQuotationMarks($('#phone').val());
        var mobilePhone = commonClass.replaceAllQuotationMarks($('#mobilePhone').val());
        var address = commonClass.replaceAllQuotationMarks($('#address').val());
        var addressState = commonClass.replaceAllQuotationMarks($('#addressState').val());
        var isDefault = commonClass.replaceAllQuotationMarks($('#isDefault').val());

        if (addressType.length == 0 || contact.length == 0 || address.length == 0 || addressState.length == 0 || isDefault.length == 0 || ( phone.length == 0 && mobilePhone.length == 0)) {
            return swal(ICICLELangUtil.getText("", 1164), "", "warning");
        }
        if ($(".form_completejob").valid()) {
            var data = {
                "addressType": addressType,
                "contact": contact,
                "phone": phone,
                "mobilePhone": mobilePhone,
                "address": address,
                "addressState": addressState,
                "isDefault": isDefault,
                "storeNo": commonClass.getQueryString("id")
            };
            requestUrl = webroot + "/store/addstoreAddr";
            requestType = "POST";
            $.ajax({
                url: requestUrl,
                type: requestType,
                data: JSON.stringify(data),
                contentType: "application/json;charset=utf-8",
                success: function (response) {
                    if (response.code == 200) {
                        swal({
                                title: "店铺地址添加成功",
                                text: '',
                                type: "success",
                                showCancelButton: false,
                                confirmButtonClass: "btn-success",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            },
                            function () {
                                location.reload(true);
                            });
                    } else {
                        swal({
                                title: response.message,
                                text: '',
                                type: "error",
                                showCancelButton: false,
                                confirmButtonClass: "btn-success",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            },
                            function () {
                                location.reload(true);
                            });
                    }
                },
                error: function (response) {
                    swal("失败", "网络连接断开，请稍后再试", "error");
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $('#loading').modal('hide');
                }
            });
        }
    });

    $("#saveStoreAddr").click(function(){
        var addrAdressType = commonClass.replaceAllQuotationMarks($("#addrAdressType").val());
        var addrContact = commonClass.replaceAllQuotationMarks($("#addrContact").val());
        var addrPhone = commonClass.replaceAllQuotationMarks($("#addrPhone").val());
        var addrMobilePhone = commonClass.replaceAllQuotationMarks($("#addrMobilePhone").val());
        var addrAddress = commonClass.replaceAllQuotationMarks($("#addrAddress").val());
        var status = $("input[name=addressState]:checked").val();
        var addrDefault = $("input[name=addrIsDefault]:checked").val();
        var storeAddrId = $("#storeAddrId").val();

        if (addrAdressType.length == 0 || addrContact.length == 0 || addrAddress.length == 0 || (addrPhone.length == 0 && addrMobilePhone.length == 0)) {
            return swal(ICICLELangUtil.getText("", 1164), "", "warning");
        }else{
            var requestData = {
                "addressId": storeAddrId,
                "addressType": addrAdressType,
                "contact": addrContact,
                "phone": addrPhone,
                "mobilePhone": addrMobilePhone,
                "address": addrAddress,
                "addressState": status,
                "isDefault": addrDefault
            };
            $.ajax({
                type: "PUT",
                contentType: "application/json;charset=utf-8",
                url: webroot + "/store/updateStoreAddr",
                data: JSON.stringify(requestData),
                success: function (response) {
                    if (response.code == 200) {
                        swal({
                                title: "成功！",
                                text: "修改成功",
                                type: "success",
                                showCancelButton: false,
                                confirmButtonClass: "btn-success",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            },
                            function () {
                                location.reload();
                            });
                    }else {
                        swal({
                                title: response.message,
                                text: "",
                                type: "error",
                                showCancelButton: false,
                                confirmButtonClass: "btn-success",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            },
                            function () {
                                $('#storeAddrUpdateDiv').modal('hide');
                                location.reload();
                                // initStoreAddrList();

                            });
                    }
                },
                error: function () {
                    swal("发送失败!", "网络中断", "error");
                },
                beforeSend: function (XMLHttpRequest) {
                    $('#loading').modal('show');
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $('#loading').modal('hide');
                }
            });

        }
    });
})

function loadStoreAddrLists(addressTypeValue){
    $("#addrAdressType").html("");
    $.ajax({
        url: webroot + "/store/storeAddrTypeList",
        type: "GET",
        success: function (response) {
            if (response.code == 200) {
                var element = "";
                var storeAddrTypeList = response.data;
                if (storeAddrTypeList.length > 0) {
                    for (var i = 0; i < storeAddrTypeList.length; i++) {
                        var storeAddr = storeAddrTypeList[i];
                        element+= "<option value='" + storeAddr.addressType + "'>" + storeAddr.addressTypeName + "</option>";
                    }
                    $("#addrAdressType").append(element);
                    $("#addrAdressType").val(addressTypeValue);
                }
            } else {
                swal("失败", "地址数据拉取失败", "error");
            }
        },
        error: function (response) {
            swal("失败", "地址数据拉取失败", "error");
        }
    });
}

function loadStoreAddrList(id){
    $.ajax({
        url: webroot + "/store/storeAddrTypeList",
        type: "GET",
        success: function (response) {
            if (response.code == 200) {
                var element = "";
                var storeAddrTypeList = response.data;
                if (storeAddrTypeList.length > 0) {
                    for (var i = 0; i < storeAddrTypeList.length; i++) {
                        var storeAddr = storeAddrTypeList[i];
                        element+= "<option value='" + storeAddr.addressType + "'>" + storeAddr.addressTypeName + "</option>";
                    }
                    $("#"+id).append(element);
                }
            } else {
                swal("失败", "地址数据拉取失败", "error");
            }
        },
        error: function (response) {
            swal("失败", "地址数据拉取失败", "error");

        }
    });
}
