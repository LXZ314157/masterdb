var FormValidation = function () {
    var handleValidationRegister = function () {
        var formreg = $(".form_completejob");
        var errorreg = $('.alert-danger', formreg);
        var successreg = $('.alert-success', formreg);
        formreg.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",  // validate all fields including form hidden input
            rules: {
                // storeId: {
                //     required: true,
                //     validId: true,
                //     maxlength: 20
                // },
                storeName: {
                    required: true
                },
                storeShortName: {
                    required: true
                },
                storeLevel: {
                    required: true
                },
                storeType: {
                    required: true
                },
                storeSpell: {
                    spellFirst: true,
                    required: true
                },
                type: {
                    required: true
                },
                // storeLevel: {
                //     required: true
                // },
                tel: {
                    phoneCN: true
                },
                fax: {
                    required: false
                },
                departId: {
                    required: true
                },
                startTime: {
                    required: true,
                    date: true
                },
                endTime: {
                    required: false,
                    date: true
                },
                mobile: {
                    mobilephoneCN: true
                },
                mobile: {
                    phoneCN: true
                },
                email: {
                    email: true
                },
                contractPeriod: {
                    digits: true
                },
                cityId: {
                    required: true
                },
                area: {
                    number: true,
                },
                floor: {
                    number: true,
                },
                zone: {
                    required: true
                },
                buyerId: {
                    required: true
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
        //main function to initiate the module
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
            //focusInvalid: false, // do not focus the last invalid input
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
                Int: {
                    digits: true
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
var buyerVOList = [];
var productLineLists = [];
var cityVOList = [];
var id = commonClass.getQueryString("id");
var zoneVOList = [];
var storeLevelVOList = [];
var storeTypeList = [];
var storeOwnerShipList = [];
var storeClassList = [];
var storeCategoryList = [];
var logoVersionList = [];
var mallTypeList = [];
var addressTypeList = [];
var storeImageCategoryList = [];
var installVersionList = [];
// var viewStoreProductLineList = [];
var storeAttribDefList = [];
var storeAttributeList = [];
var productLineList = [];
var costcenterList = [];
var manageCenterList = [];
var companyList = [];
var managerList = [];
var compareStoreList = [];
var compareByStoreNoList = [];
var respcenterList = [];
var businessUnitVOList = [];
var flagAdd = true;
var attrItemList = [];
$(function () {
    commonClass.initDatePicker();
    loadStoreAddrList("addrAdressType");
    if (jQuery.validator) {
        jQuery.validator.addMethod("mobilephoneCN", function (value, element) {
            return this.optional(element) || commonClass.checkMobilePhone(value);
        }, "手机号码格式错误");
        jQuery.validator.addMethod("phoneCN", function (value, element) {
            return this.optional(element) || commonClass.checkPhoneCN(value);
        }, "电话号码格式错误");
        jQuery.validator.addMethod("validId", function (value, element) {
            return this.optional(element) || commonClass.checkId(value);
        }, "店铺id只能为字母和数字的组合");
        jQuery.validator.addMethod("spellFirst", function (value, element) {
            return this.optional(element) || commonClass.checkSpellFirst(value);
        }, "拼音首字母格式错误");
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
    $("#loading").modal('show');
    $("#addaddressdiv").hide();
    $("#savedaddressdiv").hide();

    //加载所有的下拉列表框
    $.ajax({
        url: webroot + "/store/getdropdownlist",
        type: "POST",
        success: function (response) {
            if (response.code == 200) {
                buyerVOList = response.data.buyerVOList;
                productLineLists = response.data.productLineLists;
                cityVOList = response.data.cityVOList;
                zoneVOList = response.data.zoneVOList;
                storeLevelVOList = response.data.storeLevelVOList;
                storeTypeList = response.data.storeTypeList;
                storeOwnerShipList = response.data.storeOwnerShipList;
                storeClassList = response.data.storeClassList;
                storeCategoryList = response.data.storeCategoryList;
                logoVersionList = response.data.logoVersionList;
                mallTypeList = response.data.mallTypeList;
                addressTypeList = response.data.addressTypeList;
                storeImageCategoryList = response.data.storeImageCategoryList;
                installVersionList = response.data.installVersionList;
                businessUnitVOList = response.data.businessUnitVOList;
                managerList = response.data.managerList;
                costcenterList = response.data.costcenterList;
                respcenterList = response.data.respcenterList;
                manageCenterList = response.data.managerCenterList;
                companyList = response.data.companyList;
                compareStoreList = response.data.compareStoreList;
                loadBuyerList();
                loadCityList();
                loadZoneList();
                loadLevelList();
                loadStoreTypeList();
                loadStoreOwnerShipList();
                loadStoreClassListList();
                loadStoreCategoryList();
                loadMallTypeList();
                loadAddressTypeList();
                loadLogoVersionList();
                loadStoreImageCategoryList();
                loadInstallVersionList();
                loadBusinessUnitVOList();
                getStoreById(id);//基本属性
                getStoreDefine();//扩展属性
                loadManagerList();
                loadCostcenterList();
                loadRespcenterList();
                loadCompanyList();
                loadManageCenterList();
                loadProductLineLists();
                loadCompareStoreList();
            } else {
                console.log("数据拉取失败");
            }
        },
        error: function (response) {
            console.log("数据拉取失败");
        }
    });

    function getStoreDefine() {
        $.ajax({
            url: webroot + "/store/storeattribdefinedvo",
            type: "POST",
            success: function (response) {
                if (response.code == 200) {
                    storeAttribDefList = response.data;
                    getStoreAttrItemList();
                } else {
                    console.log("数据拉取失败");
                }
            },
            error: function (response) {
                console.log("数据拉取失败");
            }
        });
    }

    //加载itemList
    function getStoreAttrItemList() {
        $.ajax({
            url: webroot + "/store/listattributeitem",
            type: "POST",
            success: function (response) {
                if (response.code == 200) {
                    attrItemList = response.data;
                    loadAttribDef();
                    $("#loading").modal('hide');
                } else {
                    swal("失败", response.message, "error");
                    $("#loading").modal('hide');
                }
            },
            error: function (response) {
                console.log("数据拉取失败");
                $("#loading").modal('hide');
            }
        });
    }


    function getStoreById() {
        if (id != null) {
            $.ajax({
                url: webroot + "/store/find",
                contentType: "application/json;charset=utf-8",
                data: {
                    "id": id
                },
                type: "GET",
                success: function (response) {
                    if (response.code == 200) {
                        if (response.data != null) {
                            flagAdd = false;
                            storeAttributeList = response.data.storeAttributeList;
                            productLineLists = response.data.productLineLists;
                            compareByStoreNoList = response.data.compareByStoreNoList;
                            managerList = response.data.managerList;
                            productLineList = response.data.productLineList;
                            loadStore(response.data);
                        }
                    } else {
                        console.log("数据拉取失败");
                    }
                },
                error: function (response) {
                    swal("失败", "网络连接断开，请稍后再试", "error");
                    console.log("数据拉取失败");
                }
            });
        }
    }



    FormValidation.init();
    FormValidation1.init();
    defValidation.init();
    $("#next1").click(function () {
        if ($(".form_completejob").valid() && $(".def_completejob").valid()) {
            var storeId = $("#storeId").val();
            if(storeId!=null && storeId!=""){
                if (flagAdd) {
                    var regExp = /^([0-9]|[a-zA-Z]){5,10}$/;
                    if(!regExp.test(storeId)){
                        return swal("店铺ID格式不正确，必须为5到10位数字或字母", "", "warning");
                    }else{
                        $.ajax({
                            type: "GET",
                            contentType: "application/json;charset=utf-8",
                            url: webroot + "/store/verifysotreid",
                            data: {
                                "storeId":storeId
                            },
                            success: function (response) {
                                if (response.code != 200) {
                                    return swal("店铺ID已存在，请重新输入", "", "warning");
                                }else{
                                    $("#stepfirst").hide();
                                    $("#firstdiv").hide();
                                    $("#stepsecond").hide();
                                    // $("#stepthird").show();

                                    $("#addaddressdiv").show();
                                    $("#savedaddressdiv").show();
                                    $("#buttion2").show();
                                    $("#first-danger").hide();
                                    $("#third-danger").hide();
                                }
                            }
                        });
                    }
                }else{
                    $("#stepfirst").hide();
                    $("#firstdiv").hide();
                    $("#stepsecond").hide();
                    // $("#stepthird").show();

                    $("#addaddressdiv").show();
                    $("#savedaddressdiv").show();
                    $("#buttion2").show();
                    $("#first-danger").hide();
                    $("#third-danger").hide();
                }
            }else{
                $("#firstdiv").hide();
                $("#stepfirst").hide();
                $("#stepsecond").hide();
                // $("#stepthird").show();

                $("#addaddressdiv").show();
                $("#savedaddressdiv").show();
                $("#first-danger").hide();
                $("#third-danger").hide();
                $("#buttion2").show();
            }

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
            FormValidation1.init();
            initStoreAddrList();
        }
    });

    $("#endTime").change(function () {
        $("#endTime").valid();
    });
    $("#startTime").change(function () {
        $("#startTime").valid();
    });
    $("#cityId").change(function () {
        $("#cityId").valid();
    });
    $("#buyerId").change(function () {
        $("#buyerId").valid();
    });
    $("#storeType").change(function () {
        $("#storeType").valid();
    });
    $("#zone").change(function () {
        $("#zone").valid();
    });
    $("#storeLevel").change(function () {
        $("#storeLevel").valid();
    });

    $("#compareStoreNo").change(function () {
        $("#compareStoreNo").valid();
    });
    $("#before").click(function () {
        $("#stepsecond").hide();
        $("#stepthird").hide();
        $("#stepfirst").hide();
        $("#firstdiv").hide();
        $("#addaddressdiv").show();
        $("#savedaddressdiv").show();
    });

    $("#before1").click(function () {
        $("#stepsecond").hide();
        $("#stepthird").hide();
        $("#stepfirst").show();
        $("#stepsecond").show();
        $("#firstdiv").show();

        $("#savedaddressdiv").hide();
        $("#addaddressdiv").hide();
        $("#first-danger").hide();
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
        if (addrAdressType.length == 0 || addrContact.length == 0 || addrAddress.length == 0 || (addrPhone.length == 0 && addrMobilePhone.length == 0 )) {
            return  swal(ICICLELangUtil.getText("", 1164), "", "warning");
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
                                $('#storeAddrUpdateDiv').modal('hide');
                                // location.reload();
                                initStoreAddrList();

                            });
                    } else {
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
                                // location.reload();
                                initStoreAddrList();
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

    //保存地址
    $("#addrsave").click(function () {
        var hassaved = $("#hassaved").val();
        if(hassaved == "0" && flagAdd ==  true){//新增地址且没有保存过店铺（新增店铺）
            var addressType = commonClass.replaceAllQuotationMarks($("#addressType").val());
            var contact = commonClass.replaceAllQuotationMarks($('#contact').val());
            var phone = commonClass.replaceAllQuotationMarks($('#phone').val());
            var mobilePhone = commonClass.replaceAllQuotationMarks($('#mobilePhone').val());
            var address = commonClass.replaceAllQuotationMarks($('#address').val());
            var addressState = commonClass.replaceAllQuotationMarks($('#addressState').val());
            var isDefault = commonClass.replaceAllQuotationMarks($('#isDefault').val());
            if (addressType.length == 0 || contact.length == 0 || address.length == 0 || addressState.length == 0 || isDefault.length == 0 || ( phone.length == 0 && mobilePhone.length == 0)) {
                swal(ICICLELangUtil.getText("", 1164), "", "warning");
                return;
            }
            saveorupdatestore();
        } else if(hassaved != "0" || flagAdd == false){//已经保存过地址或是修改店铺
            addrsaves();
        }
    });

    function addrsaves(){
        var storeNo = id == null?$("#hassaved").val():id;
        var addressType = commonClass.replaceAllQuotationMarks($("#addressType").val());
        var contact = commonClass.replaceAllQuotationMarks($('#contact').val());
        var phone = commonClass.replaceAllQuotationMarks($('#phone').val());
        var mobilePhone = commonClass.replaceAllQuotationMarks($('#mobilePhone').val());
        var address = commonClass.replaceAllQuotationMarks($('#address').val());
        var addressState = commonClass.replaceAllQuotationMarks($('#addressState').val());
        var isDefault = commonClass.replaceAllQuotationMarks($('#isDefault').val());
        if (addressType.length == 0 || contact.length == 0 || address.length == 0 || addressState.length == 0 || isDefault.length == 0 || ( phone=='' && mobilePhone=='') ) {
            return swal(ICICLELangUtil.getText("", 1164), "", "warning");
        }
        if ($(".form_address").valid()) {
            var data = {
                "addressType": addressType,
                "contact": contact,
                "phone": phone,
                "mobilePhone": mobilePhone,
                "address": address,
                "addressState": addressState,
                "isDefault": isDefault,

                "storeNo": storeNo
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
                                initStoreAddrList();
                                clearaddress();
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
                                initStoreAddrList();
                                clearaddress();
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
    }

    function clearaddress(){
        $('#addressType').val(['']).trigger('change');
        $('#addressState').val(['']).trigger('change');
        $('#isDefault').val(['']).trigger('change');
        $("#contact").val("");
        $("#phone").val("");
        $("#mobilePhone").val("");
        $("#address").val("");


    }
    //店铺保存或修改
    function saveorupdatestore(){
        var requestUrl;
        var requestType;
        // var manageCenterId = $("#manage_center_id").find("option:selected").attr("data-itemcode");
        // var companyId = $("#company_id").find("option:selected").attr("data-itemcode");
        var respCenterId = $("#respCenter").val();
        var costCenterId = $("#costCenter").val();
        var manageCenterId = $("#manageCenter").val();
        var companyId = $("#company").val();
        var compareStoreNos = getCompareStoreNos($("#compareStoreNo").val());
        var productLine = getProductLintParams($("#productLine").val());
        var storeNo = commonClass.replaceAllQuotationMarks($("#storeNo").val());
        var storeId = commonClass.replaceAllQuotationMarks($('#storeId').val());
        var storeName = commonClass.replaceAllQuotationMarks($('#storeName').val());
        var storeShortName = commonClass.replaceAllQuotationMarks($('#storeShortName').val());
        var storeSpell = commonClass.replaceAllQuotationMarks($('#storeSpell').val());
        var buyer = commonClass.replaceAllQuotationMarks($('#buyer').val());
        var storeConnect = commonClass.replaceAllQuotationMarks($("#storeConnect").val());
        var zone = commonClass.replaceAllQuotationMarks($('#zone').val());
        var storeTypeId = commonClass.replaceAllQuotationMarks($('#storeType').val());
        var status = commonClass.replaceAllQuotationMarks($('#status .active input').val());
        var storeLevelId = $('#storeLevel').val();
        var tel = commonClass.replaceAllQuotationMarks($('#tel').val());
        var fax = commonClass.replaceAllQuotationMarks($('#fax').val());
        var mobile = commonClass.replaceAllQuotationMarks($('#mobile').val());
        var startTime = commonClass.replaceAllQuotationMarks($('#startTime').val());
        var endTime = commonClass.replaceAllQuotationMarks($('#endTime').val());
        var contractPeriod = commonClass.replaceAllQuotationMarks($('#contractPeriod').val());
        var email = commonClass.replaceAllQuotationMarks($('#email').val());
        var cityId = commonClass.replaceAllQuotationMarks($("#cityId").select2("val"));
        var area = commonClass.replaceAllQuotationMarks($('#area').val());
        var extra = commonClass.replaceAllQuotationMarks($('#extra').val());
        var buyerId = commonClass.replaceAllQuotationMarks($("#buyerId").val());
        var departId = commonClass.replaceAllQuotationMarks($("#departId").val());
        var buId = commonClass.replaceAllQuotationMarks($("#unitId").val());
        var hassaved = $("#hassaved").val();//是否保存过店铺
        var syn = "";

        if(hassaved != "0" || flagAdd == false){//保存过店铺或者是修改店铺
            syn = $("#synSetting").val();
        }
        if(hassaved == "0"){
            var address = $("#address").val();
            if(address == ""){
                syn = $("#synSetting").val();
            }else{
                syn = "";
            }
        }
        var storeOwnership = commonClass.replaceAllQuotationMarks($("#storeOwnership").val());
        var storeCategory = commonClass.replaceAllQuotationMarks($("#storeCategory").val());
        var mallType = commonClass.replaceAllQuotationMarks($("#mallType").val());
        var storeImageCategory = commonClass.replaceAllQuotationMarks($("#storeImageCategory").val());
        var logoVersion = commonClass.replaceAllQuotationMarks($("#logoVersion").val());
        var installVersion = commonClass.replaceAllQuotationMarks($("#installVersion").val());
        var storeAddress = commonClass.replaceAllQuotationMarks($("#storeAddress").val());
        var floor = commonClass.replaceAllQuotationMarks($("#floor").val());
        var deposit = commonClass.replaceAllQuotationMarks($("#deposit").val());
        var rental = commonClass.replaceAllQuotationMarks($("#rental").val());
        var salePoint = commonClass.replaceAllQuotationMarks($("#salePoint").val());
        var storeClass = commonClass.replaceAllQuotationMarks($("#storeClass").val());
        var managerNum = ($("#managerNum").val());
        var recentResetTime = commonClass.replaceAllQuotationMarks($("#recentResetTime").val());
        var decorationStandard = commonClass.replaceAllQuotationMarks($("#decorationStandard").val());
        var decorationLimitYears = commonClass.replaceAllQuotationMarks($("#decorationLimitYears").val());
        var storeAddressLongitude = commonClass.replaceAllQuotationMarks($("#storeAddressLongitude").val());
        var storeAddressLatitude = commonClass.replaceAllQuotationMarks($("#storeAddressLatitude").val());
        //获取第一部分的参数
        var storeAttributeList = [];
        for (var i = 0; i < storeAttribDefList.length; i++) {
            var storeAttribDef = storeAttribDefList[i];
            var code = $("#" + $.trim(storeAttribDef.storeAttribDefCode)).val();
            var itemcode=$("#" + $.trim(storeAttribDef.storeAttribDefCode)).find('option:selected').data('itemcode');
            if (storeAttribDef.hasItem) {
                for (var j = 0; j < attrItemList.length; j++) {
                    var item = attrItemList[j];
                    if (item.storeAttribItemId == $("#" + storeAttribDef.storeAttribDefCode).val() && item.storeAttribItemId.length != 0) {
                        storeAttributeList.push({
                            id: $("#" + storeAttribDef.storeAttribDefCode).data("id"),
                            storeNo: $("#storeNo").val(),
                            attribDefId: storeAttribDef.storeAttribDefId,//storeAttribDefId
                            attribItemId: item.storeAttribItemId,
                            storeAttribValue: itemcode
                        });
                    }
                }
            } else {
                if ($("#" + storeAttribDef.storeAttribDefCode).val().length != 0) {
                    storeAttributeList.push({
                        id: $("#" + storeAttribDef.storeAttribDefCode).data("id"),
                        storeNo: $("#storeNo").val(),
                        attribDefId: storeAttribDef.storeAttribDefId,
                        attribItemId: 0,
                        storeAttribValue: code
                    });
                }
            }
        }

        var data = {
            "storeNo": storeNo,
            "contractPeriod": contractPeriod,
            "proDeptId": departId,
            "buyerId": buyerId,
            "storeId": storeId,
            "storeName": storeName,
            "storeShortName": storeShortName,
            "storeSpell": storeSpell,
            "storeTypeId": storeTypeId,
            "storeLevelId": storeLevelId,
            "storeContact": storeConnect,
            "storePhone": tel,
            "storeFax": fax,
            "storeMobile": mobile,
            "storeEmail": email,
            "storeDesc": extra,
            "zoneId": zone,
            "cityId": cityId,
            "openDate": startTime,
            "closeDate": endTime,
            "storeSize": area,
            "storeState": status,
            "managerNum": managerNum,
            "buId": buId,
            "syn": syn,
            "storeOwnership": storeOwnership,
            "storeCategory": storeCategory,
            "mallType": mallType,
            "storeImageCategory": storeImageCategory,
            "logoVersion": logoVersion,
            "installVersion": installVersion,
            "manageCenterId": manageCenterId,
            "companyId": companyId,
            "respCenterId": respCenterId,
            "costCenterId": costCenterId,
            "compareStoreNos": compareStoreNos,
            "storeAddress": storeAddress,
            "floor": floor,
            "deposit": deposit,
            "rental": rental,
            "salePoint": salePoint,
            "storeClass": storeClass,
            "productLine": productLine,
            "recentResetTime": recentResetTime,
            "decorationStandard": decorationStandard,
            "decorationLimitYears": decorationLimitYears,
            "storeAddressLongitude": storeAddressLongitude,
            "storeAddressLatitude": storeAddressLatitude,
            "storeAttributeList": storeAttributeList
        };
        //如果没有传storeID表示是新增店铺 否则是更新店铺 更新和新增的url地址区分开来
        if (flagAdd) {
            requestUrl = webroot + "/store/addstoredata";
            requestType = "POST";
        } else {
            requestUrl = webroot + "/store/update";
            requestType = "PUT";
        }

        $.ajax({
            url: requestUrl,
            type: requestType,
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            success: function (response) {
                var no = "";
                if (response.code == 200) {
                    var msg;
                    if (flagAdd) {
                        msg = response.data[0];
                        var address = $("#address").val();
                        if(address!=""){
                            $("#hassaved").val(response.data[1]);
                            $("#storeNo").val(response.data[1]);
                            $("#storeId").val(response.data[2]);
                            flagAdd = false;
                            return addrsaves();
                        }
                    } else {
                        msg = response.data[0];
                        no = response.data[1];
                    }
                    swal({
                            title: "成功",
                            text: msg,
                            type: "success",
                            showCancelButton: false,
                            confirmButtonClass: "btn-success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        },
                        function () {
                            if (flagAdd) {
                                location.href = webroot + "/store/addstore?id=" + response.data[1];
                            } else {
                                location.href = webroot + "/store/addstore?id=" + no;
                            }
                        });

                } else {
                    swal(response.message, "", "error");
                }
            },
            error: function (response) {
                swal("失败", "网络连接断开，请稍后再试", "error");
                console.log("数据拉取失败");
            },
            beforeSend: function (XMLHttpRequest) {
                $("#save").attr("disabled", "disabled");
                $('#loading').modal('show');
            },
            complete: function (XMLHttpRequest, textStatus) {
                $("#save").removeAttr("disabled");
                $('#loading').modal('hide');
            }
        });
    }
    $("#save").click(function () {
        saveorupdatestore();
    });

    $("#way").change(function () {
        var val = $(this).val();
        if (val == 3) {
            $("#phonetype1").show();
            $("#phonetime1").show();
        } else {
            $("#phonetype1").hide();
            $("#phonetime1").hide();
        }
    });

    $('.icheck').on('ifChecked', function (event) {
        $('#contents').val(event.target.defaultValue.replace('$', $('#name').val()));
    });
});

function loadBuyerList() {
    console.log(buyerVOList);
    for (var i = 0; i < buyerVOList.length; i++) {
        var buyer = buyerVOList[i];
        var html = "<option value='" + buyer.buyerId + "'>" + buyer.buyerName + "</option>";
        $("#buyer").append(html);
    }
}

function loadCityList() {
    for (var i = 0; i < cityVOList.length; i++) {
        var city = cityVOList[i];
        var html = "<option value='" + city.cityId + "'>" + city.cityName + "</option>";
        $("#city").append(html);
    }
}

function loadZoneList() {
    for (var i = 0; i < zoneVOList.length; i++) {
        var zone = zoneVOList[i];
        var html = "<option value='" + zone.zoneId + "'>" + zone.zoneName + "</option>";
        $("#zone").append(html);
    }
}

function loadLevelList() {
    for (var i = 0; i < storeLevelVOList.length; i++) {
        var storeLevel = storeLevelVOList[i];
        var html = "<option value='" + storeLevel.storeLevelId + "'>" + storeLevel.storeLevel + "</option>";
        $("#storeLevel").append(html);
    }
}

function loadStoreTypeList() {
    for (var i = 0; i < storeTypeList.length; i++) {
        var storeType = storeTypeList[i];
        var html = "<option value='" + storeType.storeTypeId + "'>" + storeType.storeTypeName + "</option>";
        $("#storeType").append(html);
    }
}


function loadStoreOwnerShipList() {
    for (var i = 0; i < storeOwnerShipList.length; i++) {
        var storeOwnerShip = storeOwnerShipList[i];
        var html = "<option value='" + storeOwnerShip.store_attrib_item_code + "'>" + storeOwnerShip.store_attrib_item_name + "</option>";
        $("#storeOwnership").append(html);
    }
}

function loadStoreClassListList() {
    for (var i = 0; i < storeClassList.length; i++) {
        var storeClass = storeClassList[i];
        var html = "<option value='" + storeClass.store_attrib_item_code + "'>" + storeClass.store_attrib_item_name + "</option>";
        $("#storeClass").append(html);
    }
}
function loadStoreCategoryList() {
    for (var i = 0; i < storeCategoryList.length; i++) {
        var storeCategory = storeCategoryList[i];
        var html = "<option value='" + storeCategory.store_attrib_item_code + "'>" + storeCategory.store_attrib_item_name + "</option>";
        $("#storeCategory").append(html);
    }
}

function loadAddressTypeList() {
    for (var i = 0; i < addressTypeList.length; i++) {
        var addressType = addressTypeList[i];
        var html = "<option value='" + addressType.store_attrib_item_code + "'>" + addressType.store_attrib_item_name + "</option>";
        $("#addressType").append(html);
    }
}


function loadMallTypeList() {
    for (var i = 0; i < mallTypeList.length; i++) {
        var mallType = mallTypeList[i];
        var html = "<option value='" + mallType.store_attrib_item_code + "'>" + mallType.store_attrib_item_name + "</option>";
        $("#mallType").append(html);
    }
}


function loadLogoVersionList() {
    for (var i = 0; i < logoVersionList.length; i++) {
        var logoVersion = logoVersionList[i];
        var html = "<option value='" + logoVersion.store_attrib_item_code + "'>" + logoVersion.store_attrib_item_name + "</option>";
        $("#logoVersion").append(html);
    }
}
function loadStoreImageCategoryList() {
    for (var i = 0; i < storeImageCategoryList.length; i++) {
        var storeImageCategory = storeImageCategoryList[i];
        var html = "<option value='" + storeImageCategory.store_attrib_item_code + "'>" + storeImageCategory.store_attrib_item_name + "</option>";
        $("#storeImageCategory").append(html);
    }
}
function loadInstallVersionList() {
    for (var i = 0; i < installVersionList.length; i++) {
        var installVersion = installVersionList[i];
        var html = "<option value='" + installVersion.store_attrib_item_code + "'>" + installVersion.store_attrib_item_name + "</option>";
        $("#installVersion").append(html);
    }
}

function loadBusinessUnitVOList() {
    for (var i = 0; i < businessUnitVOList.length; i++) {
        var businessUnit = businessUnitVOList[i];
        var html = "<option value='" + businessUnit.buId + "'>" + businessUnit.buName + "</option>";
        $("#unitId").append(html);
    }
}

function loadAttribDef() {
    var html = "<div class=\"form-group col-md-12\">";
    for (var i = 0; i < storeAttribDefList.length; i++) {
        var storeAttribDef = storeAttribDefList[i];
        html += "<div class=\"form-group col-md-6\">";
        html += "<label class=\"control-label col-md-4\">" + storeAttribDef.storeAttribDefName + "</label>";
        html += "<div class=\"col-md-5\" >";
        html += "<div class=\"input-icon right\">";
        html += "<i class=\"fa\"></i>";
        if (storeAttribDef.hasItem) {
            var itemList = getItemList(storeAttribDef.storeAttribDefId);
            html += "<select  id=\"" + storeAttribDef.storeAttribDefCode + "\" class=\"form-control\">";
            html += "<option value=\"\">-- 请选择 --</option>";
            for (var j = 0; j < itemList.length; j++) {
                var item = itemList[j];
                html += "<option value=\"" + item.storeAttribItemId + "\"  data-itemcode='"+item.storeAttribItemCode+"'>" + item.storeAttribItemName + "</option>";
            }
            html += "</select>"
        }
        else if (storeAttribDef.itemLength > 0) {
            html += " <textarea type=\"text\" value='text' id=\"" + storeAttribDef.storeAttribDefCode + "\" class=\"form-control \"/></textarea>";
        } else {
            html += " <input  type=\"text\"  name=\"" + storeAttribDef.itemType + "\" id=\"" + storeAttribDef.storeAttribDefCode + "\" class=\"form-control\"/>";
        }
        html += "</div></div></div>";

        if(storeAttribDef.sycn){
            $("#" + storeAttribDef.storeAttribDefCode).attr("name","sycntytle");
        }
    }
    html += "</div>";
    $("#storeAttribDefArea").append(html);


    for (var i = 0; i < storeAttribDefList.length; i++) {
        var storeAttribDef = storeAttribDefList[i];
        for (var j = 0; j < storeAttributeList.length; j++) {
            var storeAttribte = storeAttributeList[j];
            if (storeAttribDef.storeAttribDefId == storeAttribte.attribDefId) {
                //storeAttribValue=上海市徐汇区虹桥路1号港汇广场三层339号\ICICLE专柜
                if (storeAttribDef.hasItem) {
                    $("#" + storeAttribDef.storeAttribDefCode).val(storeAttribte.attribItemId);
                }
                else{
                    $("#" + storeAttribDef.storeAttribDefCode).val(storeAttribte.storeAttribValue);

                }
                $("#" + storeAttribDef.storeAttribDefCode).data("id", storeAttribte.id);

            }
        }
    }
    var zoneId = $("#zone").val();
    getManagecenter(zoneId);
}

function getItemList(defId) {
    var list = [];
    for (var i = 0; i < attrItemList.length; i++) {
        var item = attrItemList[i];
        if (item.storeAttribDefId == defId) {
            list.push(item);
        }
    }
    return list;
}

function loadStore(store) {
    if(store.storeState=='1' && store.storeName.indexOf("关闭")==-1 && (store.storeTypeId=='1' || store.storeTypeId=='2')){
        $("#synSetting").append("<option selected value=\"yxt\">同步到云学堂</option>");
    }
    $("#zone").val(store.zoneId).select2();
    $("#storeLevel").val(store.storeLevelId).select2();
    $("#departId").val(store.proDeptId).select2();
    $('#buyerId').val(store.buyerId).select2();
    $('#managerNum').val(store.managerNum).select2();
    $('#respCenter').val(store.respCenterId).select2();
    $('#costCenter').val(store.costCenterId).select2();
    $('#manageCenter').val(store.manageCenterId).select2();
    $('#company').val(store.companyId).select2();
    $('#cityId').val(store.cityId).select2();
    $("#storeNo").val(store.storeNo);
    $("#unitId").val(store.buId);
    $('#storeId').val(store.storeId);
    $('#storeId').attr("disabled", "true");
    $('#actionTitle').html('编辑店铺');
    $('#storeName').val(store.storeName);
    $('#managerNum').val(store.managerNum);
    $('#storeShortName').val(store.storeShortName);
    $('#storeSpell').val(store.storeSpell);
    $('#fax').val(store.storeFax);
    $('#storeOwnership').val(store.storeOwnership).select2();
    $('#storeCategory').val(store.storeCategory).select2();
    $('#mallType').val(store.mallType).select2();
    $('#storeImageCategory').val(store.storeImageCategory).select2();
    $('#logoVersion').val(store.logoVersion).select2();
    $('#installVersion').val(store.installVersion).select2();
    $('#respCenterId').val(store.respCenterId);
    $('#costCenterId').val(store.costCenterId);
    $('#storeAddress').val(store.storeAddress);
    $('#floor').val(store.floor);
    $('#deposit').val(store.deposit);
    $('#rental').val(store.rental);
    $('#salePoint').val(store.salePoint);
    $('#storeClass').val(store.storeClass).select2();
    $('#decorationStandard').val(store.decorationStandard);
    $('#decorationLimitYears').val(store.decorationLimitYears);
    $('#storeAddressLongitude').val(store.storeAddressLongitude);
    $('#storeAddressLatitude').val(store.storeAddressLatitude);
    loadProdcutLineList();
    loadCompareByStoreNoList();
    if (store.recentResetTime != null && store.recentResetTime != 0) {
        $('#recentResetTime').datepicker("setDate", new Date(store.recentResetTime));
    }
    if (store.openDate != null && store.openDate != 0) {
        $('#startTime').datepicker("setDate", new Date(store.openDate));
    }
    if (store.closeDate != null && store.closeDate != 0) {
        $('#endTime').datepicker("setDate", new Date(store.closeDate));
    }
    $('#contractPeriod').val(store.contractPeriod);
    $('#area').val(store.storeSize);
    $('#email').val(store.storeEmail);
    $('#storeConnect').val(store.storeContact);
    $('#mobile').val(store.storeMobile);
    $('#extra').val(store.storeDesc);
    $('#storeType').val(store.storeTypeId).select2();
    $("#tel").val(store.storePhone);
    for (var i = 0; i < 4; i++) {
        $("#state_" + i).removeClass("active");
    }
    $("#state_" + store.storeState).addClass("active");
}

function setPeriod() {
    if ($("#startTime").val().length != 0 && $("#endTime").val().length != 0) {
        var b = new Date($("#startTime").val());
        var e = new Date($("#endTime").val());
        var p = e - b;
        $("#contractPeriod").val(p / (60 * 60 * 24 * 1000));
    }
}


//填充下拉框数据--对比店铺
function loadCompareStoreList(){
    $("#compareStoreNo").html("");
    var element = "<option value=''>-- 请选择 --</option>";
    if(compareStoreList.length!=0){
        for(var i=0;i<compareStoreList.length;i++){
            var storeVO = compareStoreList[i];
            element+= "<option value='"+storeVO.storeNo+"'>"+storeVO.storeName+"</option> ";
        }
    }
    $("#compareStoreNo").append(element);

}

//填充下拉框值，并根据id回显数据
function loadCompareByStoreNoList(){
    $("#compareStoreNo").html("");
    var element = "";
    if(compareStoreList.length!=0){
        for(var i=0;i<compareStoreList.length;i++){
            var storeVO = compareStoreList[i];
            element+= "<option value='"+storeVO.storeNo+"'>"+storeVO.storeName+"</option> ";
        }
    }
    $("#compareStoreNo").append(element);
    if(compareByStoreNoList!=null){
        for(var i=0;i<compareByStoreNoList.length;i++){
            var compareStoreNo = compareByStoreNoList[i].compareStoreNo;
            $("#compareStoreNo option[value='"+compareStoreNo+"']").attr("selected","selected");
        }
    }
}

function loadCostcenterList() {
    for (var i = 0; i < costcenterList.length; i++) {
        var costcenter = costcenterList[i];
        var html = "<option value='" + costcenter.costcenterId + "'>" + costcenter.costCenterName + "</option>";
        $("#costCenterId").append(html);
    }
}

function loadRespcenterList() {
    for (var i = 0; i < respcenterList.length; i++) {
        var respcenter = respcenterList[i];
        var html = "<option value='" + respcenter.respcenterId + "'>" + respcenter.respcenterName + "</option>";
        $("#respCenterId").append(html);
    }
}


function loadManageCenterList() {
    for (var i = 0; i < manageCenterList.length; i++) {
        var manageCenter = manageCenterList[i];
        var html = "<option value='" + manageCenter.managerCenterId + "'>" + manageCenter.managerCenterName + "</option>";
        $("#manageCenterId").append(html);
    }
}

function loadCompanyList() {
    for (var i = 0; i < companyList.length; i++) {
        var company = companyList[i];
        var html = "<option value='" + company.companyId + "'>" + company.companyNameLocal + "</option>";
        $("#companyId").append(html);
    }
}



function loadProductLineLists(){
    $("#productLine").html("");
    var element = "";
    if(productLineLists.length!=0){
        for(var i=0;i<productLineLists.length;i++){
            var productLine = productLineLists[i];
            element+= "<option name='productName' value='"+productLine.productLineNo+"'>"+productLine.productLineName+"</option> ";
        }
    }
    $("#productLine").append(element);
}

function loadProdcutLineList() {
    $("#productLine").html("");
    var element = "";
    if(productLineLists.length!=0){
        for(var i=0;i<productLineLists.length;i++){
            var productLine = productLineLists[i];
            element+= "<option name='productName' value='"+productLine.productLineNo+"'>"+productLine.productLineName+"</option> ";
        }
    }
    $("#productLine").append(element);

    if(productLineList.length!=0){
        for(var i=0;i<productLineList.length;i++){
            var productLineNo = productLineList[i].productLineNo;
            $("#productLine option[value='"+productLineNo+"']").attr("selected","selected");
        }
    }
}

function getProductLintParams(productLine){
    var productLineList = "";
    if(productLine!=null){
        for(var i=0;i<productLine.length;i++){
            productLineList+=productLine[i]+",";
        }
    }
    return productLineList;
}

function getCompareStoreNos(compareStoreNo){
    var compareStoreNos = "";
    if(compareStoreNo!=null){
        for(var i=0;i<compareStoreNo.length;i++){
            compareStoreNos+=compareStoreNo[i]+",";
        }
    }
    return compareStoreNos;
}



function getRespCenterId(respCenterId){
    var respStrList = "";
    if(respCenterId!=null){
        for(var i=0;i<respCenterId.length;i++){
            respStrList+=respCenterId[i]+",";
        }
    }
    return respStrList;
}
function getCostCenterId(costCenterId){
    var costStrList = "";
    if(costCenterId!=null){
        for(var i=0;i<costCenterId.length;i++){
            costStrList+=costCenterId[i]+",";
        }
    }
    return costStrList;
}


var viewStoreaddrList = [];
function initStoreAddrList(){
    $("#savedAddr").html("");
    var storeNo = id == null?$("#hassaved").val():id;
    $.ajax({
        url: webroot+"/store/findAddrListByStoreNo",
        type: "GET",
        dataType:"json",
        data:{
            "id": storeNo
        },
        success: function (response) {
            if (response.code == 200) {
                viewStoreaddrList = response.data;
                var len = viewStoreaddrList.length;
                var addrHtml = "";
                if(len>0){
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
                            "                </div>\n" +
                            "            </div>";
                        if((i+1)%2==0 || (i+1) == len){//闭
                            addrHtml+= "</div>";
                        }
                    }
                    $('#savedAddr').append(addrHtml);
                    $("#savedaddressdiv").show();
                }else{
                    $("#savedaddressdiv").hide();
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
                                initStoreAddrList();
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
                            // window.location.reload(true);
                            initStoreAddrList();
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


var FormValidation1 = function () {
    var handleValidationRegister = function () {
        var formreg = $(".form_address");
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

function stepthird(){
    $("#addaddressdiv").hide();
    $("#savedaddressdiv").hide();
    $("#stepthird").show();
    $("#firstdiv").show();
    $("#address").val("");
}

function loadManagerList() {
    for (var i = 0; i < managerList.length; i++) {
        var staff = managerList[i];
        var html = "<option value='" + staff.staffNum + "'>" + staff.staffNameLocal + "</option>";
        $("#managerNumId").append(html);
    }
}


function verifysotreid(value){
    if(value!=""){
        var regExp = /^([0-9]|[a-zA-Z]){5,10}$/;
        if(!regExp.test(value)){
            swal("店铺ID格式不正确，必须为5到10位数字或字母", "", "warning");
        }else{
            $.ajax({
                type: "GET",
                contentType: "application/json;charset=utf-8",
                url: webroot + "/store/verifysotreid",
                data: {
                    "storeId":value
                },
                success: function (response) {
                    if (response.code != 200) {
                        swal("店铺ID已存在，请重新输入", "", "warning");
                    }
                }
            });
        }
    }
}

function loadStoreAddrList(id){
    $.ajax({
        url: webroot + "/store/storeAddrTypeList",
        type: "GET",
        success: function (response) {
            if (response.code == 200) {
                var storeAddrTypeList = response.data;
                if (storeAddrTypeList.length > 0) {
                    for (var i = 0; i < storeAddrTypeList.length; i++) {
                        var storeAddr = storeAddrTypeList[i];
                        $("#"+id).append("<option value='" + storeAddr.addressType + "'>" + storeAddr.addressTypeName + "</option>");
                    }
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

function getManagecenter(zoneId){
    var manageCenterId = 0;
    for (var i = 0; i < zoneVOList.length; i++) {
        var zone = zoneVOList[i];
        if(zone.zoneId == zoneId){
            manageCenterId = zone.manageCenterId;
            break;
        }
    }
    if(manageCenterId != 0){
        $("#manageCenter").val(manageCenterId).select2();
    }
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