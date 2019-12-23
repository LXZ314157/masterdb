<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <base id="base" href="${base}">
    <meta charset="utf-8" />
    <title>主数据系统--发生错误啦</title>
    <link href="${base}/static/content/assets/global/plugins/font-awesome/css/font-awesome.min.css?v=20170227" rel="stylesheet" />
    <link href="${base}/static/content/assets/global/plugins/simple-line-icons/simple-line-icons.min.css?v=20170227" rel="stylesheet" />
    <link href="${base}/static/content/assets/global/plugins/bootstrap/css/bootstrap.min.css?v=20170227" rel="stylesheet" />
    <link href="${base}/static/content/assets/global/css/components.min.css?v=20170227" rel="stylesheet" />
    <link href="${base}/static/content/assets/global/css/plugins.min.css?v=20170227" rel="stylesheet" />
    <link href="${base}/static/content/css/css.css?v=20170227" rel="stylesheet" />
    <link href="${base}/static/content/css/error.css?v=20170227" rel="stylesheet" />
    <link rel="shortcut icon" href="${base}/static/favicon.ico"/>
</head>
<body class="page-500-full-page">
<div class="row">
    <div class="col-md-12 page-500">
        <div class=" number font-red">警告</div>
        <div class=" details">
            <h3>处理你的请求时出错.</h3>
            <p>
                ${message!''}
                <br />
            </p>
            <p>
                <a href="${base}/" class="btn red btn-outline">回到首页 </a>
                <br>
            </p>
        </div>
    </div>
</div>
</body>
</html>