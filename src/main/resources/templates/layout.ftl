<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <base id="base" href="${base}">
    <title>${title}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <link rel="shortcut icon" href="${base}/static/favicon.ico"/>
    <link href="${base}/static/content/css/css.css?v=20181017" rel="stylesheet"/>
    <link href="${base}/static/content/assets/global/plugins/font-awesome/css/font-awesome.min.css?v=20170227" rel="stylesheet"/>
    <link href="${base}/static/content/assets/global/plugins/simple-line-icons/simple-line-icons.min.css?v=20170227" rel="stylesheet"/>
    <link href="${base}/static/content/assets/global/plugins/bootstrap/css/bootstrap.min.css?v=20170227" rel="stylesheet"/>
    <link href="${base}/static/content/assets/global/plugins/select2/css/select2.min.css?v=20170227" rel="stylesheet"/>
    <link href="${base}/static/content/assets/global/plugins/select2/css/select2-bootstrap.min.css?v=20170227" rel="stylesheet"/>
    <link href="${base}/static/content/assets/global/plugins/bootstrap-sweetalert/sweetalert.css?v=20170227" rel="stylesheet"/>
    <link href="${base}/static/content/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css?v=20170227" rel="stylesheet"/>
    <link href="${base}/static/content/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css?v=20170227" rel="stylesheet"/>
    <script src="${base}/static/content/assets/global/plugins/jquery.min.js?v=20170227"></script>
    <script src="${base}/static/content/assets/global/plugins/bootstrap/js/bootstrap.min.js?v=20170227"></script>
    <script src="${base}/static/content/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js?v=20170227"></script>
    <script src="${base}/static/content/assets/global/plugins/jquery.blockui.min.js?v=20170227"></script>
    <script src="${base}/static/content/assets/global/plugins/select2/js/select2.full.min.js?v=20170227"></script>
    <script src="${base}/static/content/assets/global/plugins/bootstrap-sweetalert/sweetalert.min.js?v=20170227"></script>
    <script src="${base}/static/content/assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js?v=20170227"></script>
    <script src="${base}/static/content/assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js?v=20170227"></script>
    <script src="${base}/static/content/assets/global/plugins/moment.min.js?v=20170227"></script>
    <script src="${base}/static/content/assets/global/scripts/app.min.js?v=20170227"></script>
    <script src="${base}/static/content/assets/layouts/layout/scripts/layout.min.js?v=20170227"></script>
    <script src="${base}/static/content/assets/layouts/global/scripts/quick-sidebar.min.js?v=20170227"></script>
    <script src="${base}/static/content/assets/layouts/global/scripts/quick-nav.min.js?v=20170227"></script>
    <script src="${base}/static/content/js/common/common.js?v=20181127"></script>
    <script src="${base}/static/content/js/common/lang.js?v=20170621"></script>
    <link href="${base}/static/content/assets/global/css/components.min.css?v=20170227" rel="stylesheet"/>
    <link href="${base}/static/content/assets/layouts/layout/css/layout.min.css?v=20170227" rel="stylesheet"/>
    <link href="${base}/static/content/assets/layouts/layout/css/themes/darkblue.min.css?v=20170227" rel="stylesheet"/>
    <link href="${base}/static/content/assets/layouts/layout/css/custom.min.css?v=20170227" rel="stylesheet"/>
    <script>
        var webroot = "${base}";
        var language = "${language}";
        var imageUrl = "${image_url}";
    </script>
    <#include "${js_file_path}" ignore_missing = true />
    <link href="${base}/static/content/assets/global/css/plugins.min.css?v=20170227" rel="stylesheet"/>
    <link href="//at.alicdn.com/t/font_3rf59km111lcjtt9.css" rel="stylesheet"/>
</head>
<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white" style="height: 100%;">
    <div class="page-wrapper">
        <!-- BEGIN HEADER -->
        <div class="page-header navbar navbar-fixed-top">
            <!-- BEGIN HEADER INNER -->
            <div class="page-header-inner ">
                <!-- BEGIN LOGO -->
                <div class="page-logo">
                    <a href="http://www.icicle.com" target="_blank">
                        <svg class="logo-default" xmlns="http://www.w3.org/2000/svg" width="86" height="14"
                             viewBox="0 0 19.094 4.531">
                            <path d="M.064 1.04H.57v2.494H.064V1.04zm5.067 0h.507v2.494H5.13V1.04zm5.545 2.027V1.04h-.506v2.494h1.78v-.467h-1.275zM14.55 1.46v-.42h-1.783v2.49h1.782v-.456h-1.277v-.552h.867v-.48h-.867V1.46h1.276zM8.655 2.525c-.1.31-.392.535-.734.535-.428 0-.773-.346-.773-.773s.345-.773.772-.773c.338 0 .627.218.73.524h.535C9.07 1.446 8.55 1 7.925 1c-.71 0-1.286.578-1.286 1.287s.576 1.287 1.285 1.287c.63 0 1.155-.453 1.265-1.05h-.534zm-5.096 0c-.1.31-.392.535-.734.535-.427 0-.773-.346-.773-.773s.346-.773.773-.773c.34 0 .627.218.73.524h.535C3.974 1.446 3.454 1 2.83 1c-.71 0-1.287.578-1.287 1.287S2.12 3.573 2.83 3.573c.63 0 1.154-.453 1.265-1.05H3.56v.002zM19.03 3.79c-.173.064-.362.097-.554.097-.45 0-.852-.182-1.148-.475-.295-.295-.473-.698-.473-1.147 0-.45.182-.852.473-1.147.297-.295.7-.474 1.148-.474.19 0 .377.036.553.096V.163c-.18-.046-.364-.07-.554-.07-1.197 0-2.17.972-2.174 2.172 0 1.198.973 2.17 2.174 2.174.19 0 .373-.026.553-.073V3.79z"></path>
                            <path d="M18.525.86s-.027.058-.027.065c-.012.02-.018.03-.035.064 0 0-.086.06-.104.066 0 .004-.083.032-.087.032-.004.003-.082.018-.094.02l-.09-.01c-.007-.003-.077-.01-.09-.04-.013-.035 0-.045.01-.06.01-.014.077-.05.1-.057-.005.004.09-.02.09-.017.005-.003.112-.028.122-.032l.025-.003c-.004 0 .057-.01.057-.007 0 0 .076-.018.09-.02 0 0 .03-.012.04-.012 0 .003-.005.006-.01.013m-.303.05s0 .005 0 0m-.314.333l.117-.03s.217-.04.217-.038c.004-.003.082-.014.086-.014l.14-.02c.024-.003.05-.018.042-.014l.04-.018.06-.02c.02.002.043.013.06.038l.013.09c0 .035-.046.135-.05.145 0 0-.02.043-.03.058 0 0-.052.075-.054.075l-.11.103-.044.032c-.01.003-.117.09-.12.1.003.004.056.007.07.01.015 0 .062.008.063.004 0-.003.12.004.14.01l.083.005.074-.018.06-.008s.075-.02.08-.02c.007-.004-.01.077-.015.077-.008.017-.055.063-.06.074 0-.004-.052.046-.052.042-.018.014-.064.036-.064.036-.103.014-.35.003-.35.003-.005 0-.12-.01-.12-.014l-.152-.024-.213-.003c-.012-.003-.06-.03-.064-.03 0-.006-.015-.03-.015-.04.004-.004.012-.015.02-.015 0 .003.123-.05.13-.054.026-.014.126-.078.136-.085.01-.004.165-.154.183-.168 0-.003.12-.128.12-.132l.062-.075s-.05.02-.053.02c-.008.008-.09.037-.096.037l-.12.02-.194.033-.156-.003c-.008 0-.06-.014-.07-.01 0 0-.02-.037-.02-.065 0 .004.005-.025.017-.032-.004.003.064-.04.064-.036.002-.004.113-.03.12-.026h.03zm.924 2.08c-.016-.02-.037-.05-.055-.07-.004-.005-.02-.036-.025-.036-.002-.01-.05-.068-.053-.07l-.014-.02c-.015-.017-.11-.124-.11-.127l-.04-.05s-.027-.028-.03-.028-.108-.125-.112-.128c-.003-.007-.03-.032-.034-.032l-.03-.04c-.003-.003-.005-.032-.005-.032l.003-.04c.012-.014.03-.018.043-.025.018-.007.04-.01.043-.01.028-.008.163-.04.17-.047.01 0 .048-.03.054-.032 0 0 .04-.05.035-.046 0-.003.1-.096.106-.104.004-.007-.01-.02-.014-.02.004 0 .01-.02.01-.02 0-.002.037-.038.037-.04-.005-.005-.005-.012-.005-.016-.008 0-.035-.02-.04-.018-.007 0-.054.028-.058.028 0 0-.078.032-.084.04-.004 0-.02.006-.057.017-.004 0-.064.02-.068.02-.012.004-.05.012-.05.012-.007 0-.126.02-.132.024l-.014-.03c-.008-.017-.008-.02-.012-.03-.01-.026-.014-.04-.02-.05-.005-.008-.01-.03-.005-.033l.12-.06.073-.057c.004-.007.004-.014 0-.018 0-.007-.008-.01-.008-.018s.014-.06.014-.06c-.002-.004-.04-.004-.057 0-.006 0-.082.013-.092.013l-.215.042c-.012.004-.03.004-.037.007l-.13.03c-.03.004-.077.026-.077.026s-.02.028-.018.046c0 .01.008.014.018.032.012.018.02.032.064.06.018.016.05.02.06.022h.065c.008 0 .035-.007.04-.007.007 0 .017-.008.017-.008.004-.003.02.007.02.007.005.002.02.074.02.074l.003.01s-.004.04 0 .04l-.027.01c.002-.002-.02 0-.025 0-.033.004-.133.033-.133.033l-.148.04-.078.017c-.008.004-.02.004-.025 0 0 0-.03-.014-.034-.01-.014.003-.066.024-.07.028 0 0-.033.02-.047.05-.005.007-.01.04-.015.043.004 0 0 .032 0 .036.004 0 .01.02.014.02 0 0 .08.002.128.006h.14c.01 0 .095-.004.103-.007l.09-.02s.042-.01.046-.006c.004 0-.004.007 0 .007-.004.01-.018.022-.02.03l-.066.056c-.004.007-.064.064-.064.075l-.006.01-.057.068c0 .004-.146.178-.146.178l-.008.015c0 .008-.032.033-.032.037-.016.01-.072.075-.072.07l-.02.03c-.008.014.014.043.01.043.02.003.05-.007.053-.007.017-.012.038-.02.038-.02l.068-.04c.014-.012.018-.012.027-.02l.11-.077c.013-.007.105-.085.108-.09.007-.002.11-.095.114-.1 0 0 .018-.02.02-.02l-.003.057s-.02.216-.018.224c-.002.004-.01.078-.014.074.004.005-.01.112-.004.12.02.035.043.05.043.05 0 .003.012.013.018.013l.068-.003c.014-.003.027-.024.027-.024.004 0 .03-.05.033-.05 0-.008.025-.1.02-.107.005 0 .026-.08.026-.076 0-.02-.013-.057-.01-.06l.01-.086s0-.078.005-.078.012.018.012.018c.004.003.035.057.04.06s.024.026.03.026l.11.082c.015.01.058.043.062.043.013.01.095.07.087.066.025.015.04.02.06.036 0 .004.073.053.077.057.004.007.05.007.057.003-.003 0 .01-.01.01-.02-.016.007-.038-.033-.04-.04"></path>
                        </svg>
                    </a>
                    <div class="menu-toggler sidebar-toggler">
                        <span></span>
                    </div>
                </div>
                <!-- END LOGO -->
                <!-- BEGIN TOP NAVIGATION MENU -->
                <div class="top-menu">
                    <ul class="nav navbar-nav pull-right">
                        <!-- BEGIN USER LOGIN DROPDOWN -->
                        <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                        <li class="dropdown dropdown-user">
                            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                               data-close-others="true">
                                <span class="username username-hide-on-mobile">${lonin_name} </span>
                            </a>
                        </li>
                        <!-- END USER LOGIN DROPDOWN -->
                    </ul>
                </div>
                <!-- END TOP NAVIGATION MENU -->
            </div>
            <!-- END HEADER INNER -->
        </div>
        <!-- END HEADER -->
        <!-- BEGIN HEADER & content DIVIDER -->
        <div class="clearfix"></div>
        <!-- END HEADER & content DIVIDER -->
        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <!-- BEGIN SIDEBAR -->
            <div class="page-sidebar-wrapper">
                <!-- BEGIN SIDEBAR -->
                <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
                <div class="page-sidebar navbar-collapse collapse">
                    <!-- BEGIN SIDEBAR MENU -->
                    <ul class="page-sidebar-menu  page-header-fixed" data-keep-expanded="false" data-auto-scroll="true"
                        data-slide-speed="200" style="padding-top: 20px">
                        <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                    ${left_menu!''}
                        <!-- END SIDEBAR TOGGLER BUTTON -->
                    </ul>
                    <!-- END SIDEBAR MENU -->
                </div>
                <!-- END SIDEBAR -->
            </div>
            <!-- END SIDEBAR -->
            <!-- BEGIN content -->
            <div class="page-content-wrapper">
                <!-- BEGIN content BODY -->
                <div class="page-content">
                <#include "${body_file_path}">
                    <div class="clearfix"></div>
                    <!-- END PAGE AREA-->
                    <!-- loading -->
                    <div class="modal fade" id="loading" tabindex="-1" data-backdrop='static' data-keyboard='false'
                         data-width="175" data-height="100">
                        <div class="modal-body" style="margin: auto; text-align: center;">
                            <img src="${base}/static/content/img/updateProgress.gif" class="loading"/><br/>
                            <span>loading......</span>
                        </div>
                    </div>
                </div>
                <!-- END content BODY -->
            </div>
            <!-- END content -->
        </div>
        <!-- END CONTAINER -->
        <!-- BEGIN FOOTER -->
        <div class="page-footer">
            <div class="page-footer-inner" style="margin: auto; text-align: center; float: none; display: block;">
                &copy; 1997 - ${current_year} Shanghai ICICLE Fashion Group Co.,Ltd.
                <a target="_blank" href="http://www.icicle.com">ICICLE</a> &nbsp;|&nbsp;
                <a target="_blank" href="http://www.miitbeian.gov.cn/">沪ICP备12027313</a>
            </div>
            <div class="scroll-to-top">
                <i class="icon-arrow-up"></i>
            </div>
        </div>
        <!-- END FOOTER -->
    </div>
</body>
</html>