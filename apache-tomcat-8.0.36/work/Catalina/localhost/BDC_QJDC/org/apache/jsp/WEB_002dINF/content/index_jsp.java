/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.36
 * Generated at: 2016-09-01 09:34:35 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.content;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("<title>DevOOPS v2</title>\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("<link href=\"devoops/plugins/bootstrap/bootstrap.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"devoops/plugins/jquery-ui/jquery-ui.min.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"js/vendor/node_modules/font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"devoops/plugins/fancybox/jquery.fancybox.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"devoops/plugins/fullcalendar/fullcalendar.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"devoops/plugins/xcharts/xcharts.min.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"devoops/plugins/select2/select2.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"devoops/plugins/justified-gallery/justifiedGallery.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"devoops/plugins/chartist/chartist.min.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"css/jquery.bootgrid.min.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"//cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"js/vendor/bootStrap-addTabs/theme/css/bootstrap-addtabs.css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"js/vendor/toastr/build/toastr.min.css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("<link href=\"devoops/css/style_v2.css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->\r\n");
      out.write("<!--[if lt IE 9]>\r\n");
      out.write("\t\t\t\t<script src=\"http://getbootstrap.com/docs-assets/js/html5shiv.js\"></script>\r\n");
      out.write("\t\t\t\t<script src=\"http://getbootstrap.com/docs-assets/js/respond.min.js\"></script>\r\n");
      out.write("\t\t<![endif]-->\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\t<!--Start Header-->\r\n");
      out.write("\t<div id=\"screensaver\">\r\n");
      out.write("\t\t<canvas id=\"canvas\"></canvas>\r\n");
      out.write("\t\t<i class=\"fa fa-lock\" id=\"screen_unlock\"></i>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"modalbox\">\r\n");
      out.write("\t\t<div class=\"devoops-modal\">\r\n");
      out.write("\t\t\t<div class=\"devoops-modal-header\">\r\n");
      out.write("\t\t\t\t<div class=\"modal-header-name\">\r\n");
      out.write("\t\t\t\t\t<span>Basic table</span>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"box-icons\">\r\n");
      out.write("\t\t\t\t\t<a class=\"close-link\"> <i class=\"fa fa-times\"></i>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"devoops-modal-inner\"></div>\r\n");
      out.write("\t\t\t<div class=\"devoops-modal-bottom\"></div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<header class=\"navbar\">\r\n");
      out.write("\t<div class=\"container-fluid expanded-panel\">\r\n");
      out.write("\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t<div id=\"logo\" class=\"col-xs-12 col-sm-2\">\r\n");
      out.write("\t\t\t\t<a href=\"index\">不动产权籍调查系统</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div id=\"top-panel\" class=\"col-xs-12 col-sm-10\">\r\n");
      out.write("\t\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t\t<div class=\"col-xs-4 col-sm-8 top-panel-right\">\r\n");
      out.write("\t\t\t\t\t\t<a href=\"#\" class=\"about\">about</a>\r\n");
      out.write("\t\t\t\t\t\t<ul class=\"nav navbar-nav pull-right panel-menu\">\r\n");
      out.write("\t\t\t\t\t\t\t<li class=\"dropdown\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"#\" class=\"dropdown-toggle account\" data-toggle=\"dropdown\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"avatar\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<img src=\"devoops/img/avatar.jpg\" class=\"img-circle\" alt=\"avatar\" />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div> <i class=\"fa fa-angle-down pull-right\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"user-mini pull-right\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<span class=\"welcome\">Welcome,</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<span>Jane\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tDevoops\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t<ul class=\"dropdown-menu\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<li><a href=\"#\"> <i class=\"fa fa-user\"></i> <span>Profile</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<li><a href=\"devoops/ajax/page_messages.html\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tclass=\"ajax-link\"> <i class=\"fa fa-envelope\"></i> <span>Messages</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<li><a href=\"devoops/ajax/gallery_simple.html\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tclass=\"ajax-link\"> <i class=\"fa fa-picture-o\"></i> <span>Albums</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<li><a href=\"#\"> <i class=\"fa fa-power-off\"></i> <span>Logout</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t</header>\r\n");
      out.write("\t<!--End Header-->\r\n");
      out.write("\t<!--Start Container-->\r\n");
      out.write("\t<div id=\"main\" class=\"container-fluid\">\r\n");
      out.write("\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t<div id=\"sidebar-left\" class=\"col-xs-2 col-sm-2\">\r\n");
      out.write("\t\t\t\t<ul class=\"nav main-menu\">\r\n");
      out.write("\t\t\t\t\t<li class=\"dropdown\">\r\n");
      out.write("\t\t\t\t\t\t<a href=\"#\" class=\"dropdown-toggle\">\r\n");
      out.write("\t\t\t\t\t\t\t<i class=\"fa fa-bar-chart-o\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t<span class=\"hidden-xs\">不动产权籍调查数据录入</span>\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t\t<ul class=\"dropdown-menu\">\r\n");
      out.write("\t\t\t\t\t\t\t<li><a class=\"ajax-link\" href=\"devoops/ajax/jttdsyq.html\">集体土地所有权</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a class=\"ajax-link\" href=\"devoops/ajax/jsydsyq.html\">建设用地/宅基地使用权</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a class=\"ajax-link\" href=\"devoops/ajax/c3_table.html\">土地承包经营权</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a class=\"ajax-link\" href=\"gzwdc\">非承包方式取得的林地使用权</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a class=\"ajax-link\" href=\"devoops/ajax/c4_table.html\">海域使用权</a></li>\r\n");
      out.write("\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t<a class=\"ajax-link\" href=\"devoops/ajax/query.html\"><i class=\"fa fa-bar-chart-o\"></i>查询</a>\r\n");
      out.write("\t\t\t\t\t\t<a class=\"ajax-link\" href=\"devoops/ajax/query-view.html\"><i class=\"fa fa-bar-chart-o\"></i>视图查询</a>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"#\" class=\"dropdown-toggle\">\r\n");
      out.write("\t\t\t\t\t\t\t<i class=\"fa fa-bar-chart-o\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t<span class=\"hidden-xs\">old</span>\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t\t<ul class=\"dropdown-menu\">\r\n");
      out.write("\t\t\t\t\t\t\t<li><a class=\"ajax-link\" href=\"devoops/ajax/jttdsyq.html\">地籍调查</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a class=\"ajax-link\" href=\"devoops/ajax/c2_table.html\">土地承包经营权、农用地其他使用权调查</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a class=\"ajax-link\" href=\"devoops/ajax/c3_table.html\">集体土地所有权宗地分类面积调查</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a class=\"ajax-link\" href=\"gzwdc\">房屋调查表</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a class=\"ajax-link\" href=\"devoops/ajax/c4_table.html\">林权调查</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a class=\"ajax-link\" href=\"devoops/ajax/c4_table.html\">海籍调查</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a class=\"ajax-link\" href=\"devoops/ajax/c4_table.html\">构（建）筑物调查</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a class=\"ajax-link\" href=\"devoops/ajax/test.html\">test</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a class=\"ajax-link\" href=\"devoops/ajax/upload.html\">uploadfiles</a></li>\r\n");
      out.write("\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!--Start Content-->\r\n");
      out.write("\t\t\t<div id=\"content\" class=\"col-xs-12 col-sm-10\">\r\n");
      out.write("\t\t\t\t<div id=\"about\">\r\n");
      out.write("\t\t\t\t\t<div class=\"about-inner\">\r\n");
      out.write("\t\t\t\t\t\t<h4 class=\"page-header\">Open-source admin theme for you</h4>\r\n");
      out.write("\t\t\t\t\t\t<p>DevOOPS team</p>\r\n");
      out.write("\t\t\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\t\t\tHomepage - <a href=\"http://devoops.me\" target=\"_blank\">http://devoops.me</a>\r\n");
      out.write("\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\t\t\tEmail - <a href=\"mailto:devoopsme@gmail.com\">devoopsme@gmail.com</a>\r\n");
      out.write("\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\t\t\tTwitter - <a href=\"http://twitter.com/devoopsme\" target=\"_blank\">http://twitter.com/devoopsme</a>\r\n");
      out.write("\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t\t<p>Donate - BTC 123Ci1ZFK5V7gyLsyVU36yPNWSB5TDqKn3</p>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"preloader\">\r\n");
      out.write("\t\t\t\t\t<img src=\"devoops/img/devoops_getdata.gif\" class=\"devoops-getdata\" alt=\"preloader\" />\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div id=\"ajax-content\"></div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!--End Content -->\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!--End Container-->\r\n");
      out.write("\t<!-- jQuery (necessary for Bootstrap's JavaScript devoops/plugins) -->\r\n");
      out.write("\t<!--<script src=\"http://code.jquery.com/jquery.js\"></script>-->\r\n");
      out.write("\t<!--<script src=\"devoops/plugins/jquery/jquery.min.js\"></script>-->\r\n");
      out.write("\t<script src=\"js/jquery-1.12.3.min.js\"></script>\r\n");
      out.write("\t<script src=\"devoops/plugins/jquery-ui/jquery-ui.min.js\"></script>\r\n");
      out.write("\t<!-- Include all compiled devoops/plugins (below), or include individual files as needed -->\r\n");
      out.write("\t<script src=\"devoops/plugins/bootstrap/bootstrap.min.js\"></script>\r\n");
      out.write("\t<script\r\n");
      out.write("\t\tsrc=\"devoops/plugins/justified-gallery/jquery.justifiedGallery.min.js\"></script>\r\n");
      out.write("\t<script src=\"devoops/plugins/tinymce/tinymce.min.js\"></script>\r\n");
      out.write("\t<script src=\"devoops/plugins/tinymce/jquery.tinymce.min.js\"></script>\r\n");
      out.write("\t<!-- All functions for this theme + document.ready processing -->\r\n");
      out.write("\t<script src=\"devoops/js/devoops.js\"></script>\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\t<script src=\"js/jquery4SpringMapping.js\"></script>\r\n");
      out.write("\t<script src=\"js/jquery.bootgrid.min.js\"></script>\r\n");
      out.write("\t<script src=\"js/vendor/node_modules/lodash/lodash.min.js\"></script>\r\n");
      out.write("\t<script src=\"js/vendor/bootStrap-addTabs/theme/js/bootstrap-addtabs.js\"></script>\r\n");
      out.write("\t<script src=\"js/vendor/Bootstrap-Confirmation/bootstrap-confirmation.min.js\"></script>\r\n");
      out.write("\t<script src=\"js/vendor/toastr/build/toastr.min.js\"></script>\r\n");
      out.write("\t<script src=\"js/service/fetchData.js\"></script>\r\n");
      out.write("\t<script src=\"js/service/setData.js\"></script>\r\n");
      out.write("\t<script src=\"js/service/myJquery.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
