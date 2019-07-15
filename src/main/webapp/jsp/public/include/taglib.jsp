<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="l" uri="/tags/loushang-web"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	var ctx = '${ctx}';
</script>

<l:link
	path="css/bootstrap_new.css,css/font-awesome.css,css/jquery-ui.css,css/ui_new.css,css/form_new.css,css/datatables_new.css,css/slickgrid_new.css,css/wjgl.css" />
<!--[if lt IE 9]>
    <script src="<l:asset path='html5shiv.js'/>"></script>
    <script src="<l:asset path='respond.js'/>"></script>
  <![endif]-->
<l:script
	path="jquery.js,jquery.form.js,bootstrap.js,jquery-ui.js,form.js,arttemplate.js,ui.js,datatables.js,slickgrid.js,loushang-framework.js" />