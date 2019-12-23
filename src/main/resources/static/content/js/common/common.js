/**
 * js时间对象的格式化;
 * eg:format="yyyy-MM-dd hh:mm:ss";
 */
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1,  //month   
        "d+": this.getDate(),     //day   
        "h+": this.getHours(),    //hour   
        "m+": this.getMinutes(),  //minute   
        "s+": this.getSeconds(), //second   
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter   
        "S": this.getMilliseconds() //millisecond   
    }
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}
/**
 *js中更改日期
 * y年， m月， d日， h小时， n分钟，s秒
 */
Date.prototype.add = function (part, value) {
    value *= 1;
    if (isNaN(value)) {
        value = 0;
    }
    switch (part) {
        case "y":
            this.setFullYear(this.getFullYear() + value);
            break;
        case "m":
            this.setMonth(this.getMonth() + value);
            break;
        case "d":
            this.setDate(this.getDate() + value);
            break;
        case "h":
            this.setHours(this.getHours() + value);
            break;
        case "n":
            this.setMinutes(this.getMinutes() + value);
            break;
        case "s":
            this.setSeconds(this.getSeconds() + value);
            break;
        default:

    }
}

var commonClass = {

//初始化datepicker控件
    initDatePicker: function () {
        $(".start").datepicker(
            {
                keyboardNavigation: false,
                forceParse: false,
                autoclose: true,
                format: "yyyy-mm-dd",
                language: 'zh-CN',
                startDate: '1997-01-01',
                clearBtn: true,
                todayHighlight: true
            }).on('changeDate', function (ev) {
            if (ev.date) {
                $(".end").datepicker('setStartDate', new Date(ev.date.valueOf()));
            } else {
                $(".end").datepicker('setStartDate', null);
            }
        });
        $(".end").datepicker(
            {
                keyboardNavigation: false,
                forceParse: false,
                autoclose: true,
                format: "yyyy-mm-dd",
                language: 'zh-CN',
                endDate: '2999-12-31',
                clearBtn: true,
                todayHighlight: true
            }).on('changeDate', function (ev) {
            if (ev.date) {
                $(".start").datepicker('setEndDate', new Date(ev.date.valueOf()));
            } else {
                $(".start").datepicker('setEndDate', null);
            }
        });
    },

//初始化datetimepicker控件
    initDateTimePicker: function () {
        $(".startTime").datetimepicker(
            {
                keyboardNavigation: false,
                forceParse: false,
                autoclose: true,
                format: "yyyy-mm-dd hh:ii:ss",
                language: 'zh-CN',
                startDate: '1997-01-01',
                clearBtn: true,
                minuteStep:1,
                todayBtn:1,
                todayHighlight: true
            }).on('changeDate', function (ev) {
            if (ev.date) {
                $(".endTime").datetimepicker('setStartDate', new Date(ev.date.valueOf()));
            } else {
                $(".endTime").datetimepicker('setStartDate', null);
            }
        });
        $(".endTime").datetimepicker(
            {
                keyboardNavigation: false,
                forceParse: false,
                autoclose: true,
                format: "yyyy-mm-dd hh:ii:ss",
                language: 'zh-CN',
                endDate: '2999-12-31',
                clearBtn: true,
                minuteStep:1,
                todayBtn:1,
                todayHighlight: true
            }).on('changeDate', function (ev) {
            if (ev.date) {
                $(".startTime").datetimepicker('setEndDate', new Date(ev.date.valueOf()));
            } else {
                $(".startTime").datetimepicker('setEndDate', null);
            }
        });

    },

    getDateString: function (timestamp) {
        var date = new Date(timestamp);
        return this.date_long(date);
    },

    getDateTimeString: function (timestamp) {
        if (timestamp == null || timestamp == 0) {
            return "";
        }
        var date = new Date(timestamp);
        return this.date_time_long(date);
    },

    date_long_string: function (str) {
        if (str == null || str == "") {
            return "";
        }
        var d = eval('new ' + str.substr(1, str.length - 2));
        return d.format('yyyy-MM-dd hh:mm:ss');
    },
    date_time_long: function (str) {
        return str.format('yyyy-MM-dd hh:mm:ss');
    },
    date_long: function (str) {
        return str.format('yyyy-MM-dd');
    },
    date_string: function (str) {
        if (str == null || str == "") {
            return "";
        }
        var d = eval('new ' + str.substr(1, str.length - 2));
        return d.format('yyyy-MM-dd');
    },
    //格式化数字
    dFormat: function (i) {
        return i < 10 ? "0" + i.toString() : i;
    },
    //生成8位随机数字
    getrandompwd: function () {
        return ("0000000" + 100000000 * Math.random()).match(/(\d{8})(\.|$)/)[1];
    },
    //获取url后面带的参数
    getQueryString: function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    },
    getQueryChineseString: function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]);
        return null;
    },
    setScroll: function () {
        var offsety = $('.table-scrollable').offset().top + 100;
        $(".table-scrollable").slimScroll({
            height: document.body.clientHeight - offsety,
            size: '0px'
        });
        $(".table-scrollable").css("overflow", "auto");
        $(".slimScrollDiv").css("overflow", "visible");
    },
    checkFileSize: function (selector, fileType) {
        var fileData = $("#" + selector)[0].files[0];
        if (fileData != null) {
            if (fileData.size > 4 * 1024 * 1024) {
                if (fileType == "image") {
                    swal("错误!", "上传图片太大，请上传单个4m以下的图片!", "error");
                }
                else {
                    swal("错误!", "上传文档太大，请上传单个4m以下的文档!", "error");
                }
                return false;
            }
        }
        return true;
    },
    replaceAllQuotationMarks: function (str) {
        if (str == null || str.length === 0) {
            return "";
        }
        return str.replace(new RegExp(/(")/g), '\"').replace(new RegExp(/(')/g), '');
    },
    getStringVal: function (args) {
        if (args == null || args.length === 0) {
            return "";
        }
        else {
            if (args instanceof Array) {
                return this.replaceAllQuotationMarks(args.join(","));
            }
            else {
                return this.replaceAllQuotationMarks(args);
            }
        }
    },
    getNowFormatDate: function () {
        var date = new Date();
        return date.format('yyyy-MM-dd');
    },
    getNowFormatTime: function () {
        var date = new Date();
        date.setHours(23);
        date.setMinutes(59);
        return date.format('yyyy-MM-dd hh:mm');
    },
    getNextYearFormatDate: function () {
        var date = new Date();
        date.add("y", 1); //下一年  
        return date.format('yyyy-MM-dd');
    },
    getLastMonthFormatDate: function () {
        var date = new Date();
        date.add("m", -1); //上月
        return date.format('yyyy-MM');
    },
    checkFileSize: function (selector, fileType) {
        var fileData = $("#" + selector)[0].files[0];
        if (fileData != null) {
            if (fileData.size > 4 * 1024 * 1024) {
                if (fileType == "image") {
                    swal("错误!", "上传图片太大，请上传单个4m以下的图片!", "error");
                }
                else {
                    swal("错误!", "上传文档太大，请上传单个4m以下的文档!", "error");
                }
                return false;
            }
        }
        return true;
    },
    clearFileinput: function () {
        if ($('.fileinput').length > 0) {
            $('.fileinput').fileinput('clear');
        }
    },
    checkMobilePhone: function (value) {
        var reg = /^1[3456789]\d{9}$/;
        return reg.test(value);
    },
    checkPhoneCN: function (value) {
        var reg = /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;
        return reg.test(value)
    },
    checkSpellFirst: function (value) {
        var reg = /^[a-zA-Z]+$/;
        return reg.test(value)
    },
    checkId: function (value) {
        var reg = /^[a-zA-Z0-9]+$/;
        return reg.test(value)
    },
    checkFax: function (value) {
        var reg = /^(\d{3,4}-)?\d{7,8}$/;
        return reg.test(value)
    },
    checkCode: function (value) {
        //英文和下划线组成
        var reg = /^[a-zA-Z_]{1,}$/;
        return reg.test(value);
    },
    checkInt: function (value) {
        var reg = /^\d+$/;
        return reg.test(value);
    }
};

var UIExtendedModals = function () {
    return {
        //main function to initiate the module
        init: function () {
            // general settings
            $.fn.modal.defaults.spinner = $.fn.modalmanager.defaults.spinner =
                '<div class="loading-spinner" style="width: 200px; margin-left: -100px;">' +
                '<div class="progress progress-striped active">' +
                '<div class="progress-bar" style="width: 100%;"></div>' +
                '</div>' +
                '</div>';

            $.fn.modalmanager.defaults.resize = true;
        }
    };
}();


$(function () {
    UIExtendedModals.init();

    if ($('.date-picker').length > 0) {
        $('.date-picker').datepicker({
            rtl: App.isRTL(),
            language: 'zh-CN',
            startDate: '1997-01-01',
            // endDate:'2099-12-31',
            orientation: "left",
            autoclose: true,
            todayHighlight: true,
            todayBtn: "linked"
        });
    }

    if ($('.date-picker-fromnow').length > 0) {
        $('.date-picker-fromnow').datepicker({
            rtl: App.isRTL(),
            language: 'zh-CN',
            startDate: commonClass.getNowFormatDate(),
            endDate: '2099-12-31',
            orientation: "left",
            autoclose: true,
            todayHighlight: true,
            todayBtn: "linked"
        });
    }

    if ($('.date-picker-year').length > 0) {
        $('.date-picker-year').datepicker({
            rtl: App.isRTL(),
            language: 'zh-CN',
            startDate: '1900-01-01',
            endDate: commonClass.getNowFormatDate(),
            orientation: "left",
            startView: 2,
            autoclose: true,
            todayHighlight: true
        });
    }

    if ($('.date-picker-jobend').length > 0) {
        $('.date-picker-jobend').datepicker({
            rtl: App.isRTL(),
            language: 'zh-CN',
            startDate: '1900-01-01',
            endDate: commonClass.getNextYearFormatDate(),
            orientation: "left",
            autoclose: true,
            todayHighlight: true
        });
    }

    if ($('.datetime-picker').length > 0) {
        $('.datetime-picker').datetimepicker({
            rtl: App.isRTL(),
            language: 'zh-CN',
            startDate: '1900-01-01',
            endDate: commonClass.getNowFormatTime(),
            todayHighlight: true,
            todayBtn: "linked",
            autoclose: true,
            showMeridian: 1
        });
    }

    if ($('.date-picker-month').length > 0) {
        $('.date-picker-month').datepicker({
            rtl: App.isRTL(),
            language: 'zh-CN',
            startDate: '2017-01',
            endDate: commonClass.getLastMonthFormatDate(),
            orientation: "left",
            format: "yyyy-mm",
            startView: 1,
            minViewMode: 1,
            autoclose: true,
            todayHighlight: true
        });
    }
    if ($('#ueditor_input').length > 0) {
        UE.getEditor('ueditor_input', {
            maximumWords: 2000,
            elementPathEnabled: false
        });
    }

    $.fn.select2.defaults.set("theme", "bootstrap");
    if ($(".select2,.select2-multiple").length > 0) {
        $(".select2,.select2-multiple").select2({width: null});
    }
});