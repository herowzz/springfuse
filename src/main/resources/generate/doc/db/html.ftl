<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<head>
  <title>数据文档</title>
  <script>
    window.onload = function () {
      var praiseObjs = document.getElementById("navUl").getElementsByTagName("a");

      function clear() {
        for (var i = 0; i < praiseObjs.length; i++) {
          praiseObjs[i].setAttribute("class", "alink");
        }
      }

      function addPraiseNum() {
        for (var i = 0; i < praiseObjs.length; i++) {
          (function (i) {
            praiseObjs[i].onclick = function () {
              clear();
              this.setAttribute("class", "chgblue alink");
            }
          })(i);
        }
      }
      addPraiseNum();
    }
  </script>
  <style lang="">
    html,
    body {
      height: 100%;
      height: 100%;
      margin: 0;
      padding: 0;
    }

    ul li {
      padding: 0;
      margin: 0;
    }

    #html_doc {
      position: relative;
      width: 100%;
      height: 100%;
      font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;

    }

    #html_doc_left {
      top: 0;
      bottom: 0;
      overflow-y: auto;
      color: #364149;
      background: #fafafa;
      border-right: 1px solid rgba(0, 0, 0, .07);
      width: 300px;
      left: 0px;
      position: absolute
    }

    #html_doc_right {
      top: 0;
      right: 0;
      bottom: 0;
      overflow-y: auto;
      color: #000;
      background: #fff;
      left: 300px;
      position: absolute
    }

    #intr {
      padding: 6px;
      background: #fff;
      border-bottom: 1px solid rgba(0, 0, 0, .07);
      padding-left: 20px;
      height: 40px;
      line-height: 40px;
    }

    #navUl {
      padding: 0;
      font-size: 14px;
    }

    #navUl ul {
      padding-top: 0px;
    }

    #navUl>li {
      height: auto;
      list-style: none;
      font-family: "Open Sans", "Clear Sans", "Helvetica Neue", Helvetica, Arial, sans-serif;

    }

    #navUl>li a {
      display: block;
      border-bottom: none;
      color: #364149;
      text-overflow: ellipsis;
      overflow: hidden;
    }

    #navUl li {
      list-style: none;
    }

    #navUl li a {
      display: block;
      padding: 10px 15px;
      border-bottom: none;
      color: #364149;
      text-overflow: ellipsis;
      overflow: hidden;
      text-decoration: none;
    }

    .liwei {
      height: 30px;
    }

    #navUl>li>ul {
      padding-left: 20px;
    }

    #navUl>li>ul>li {
      list-style: none;
    }

    #navUl a:hover {
      text-decoration: underline;
    }

    /* #navUl a:active{
      color:#008cff;
      text-decoration: none;
    }  */

    #html_doc_header {
      height: 50px;
      line-height: 40px;
      padding: 0 8px;
      z-index: 2;
      color: #7e888b;
    }

    #contentcen {
      width: 100%;
      height: 100%;
    }

    #conchild {
      /* position: relative;
      max-width: 1200px;
      margin: 0 auto;
      */
      margin: 0 200px;
      padding: 20px 15px 40px 15px;
    }

    p {
      color: #333;
      text-indent: 2em;
      line-height: 1.7;
    }

    table {
      border-collapse: collapse;
    }

    th {
      min-height: 40px;
      width: 100px;
      height: 40px;
      border: 1px solid black;
      font-size: 14px;
      text-align: center;
    }

    td {
      min-height: 40px;
      border: 1px solid black;
      font-size: 14px;
      text-align: center;
    }

    #leftBottom {
      overflow: hidden;
      list-style: none;
    }

    #leftBottom>a {
      text-decoration: none;
      color: #364149;
      font-size: 14px;
      padding: 10px 15px;
      height: 40px;
      line-height: 40px;
      display: block;
      border-top: 1px solid rgba(0, 0, 0, .07);
    }

    .chgblue {
      color: #008cff !important;
      text-decoration: none;
    }
  </style>
</head>

<body>
  <div id="html_doc">
    <div id="html_doc_left" style="width:300px;left:0px;position:absolute">
      <div id="intr">${projectName} 数据表</div>
      <nav>
        <ul id="navUl">
          <#list tbMap?keys as key>
            <li>
              <a href="#${key}" class="aLink">${key}</a>
              <ul>
                <#list tbMap[key] as tb>
                  <li class="liwei">
                    <a href="#${tb.name}" class="aLink">${tb.fullName}</a>
                  </li>
                </#list>
              </ul>
            </li>
          </#list>
        </ul>
        <div id="leftBottom">
          <a href="#">Published with Springfuse</a>
        </div>
      </nav>

    </div>
    <div id="html_doc_right" style="left:300px;position:absolute">
      <div id="html_doc_header">
        <a href="#"></a>
        <a href="#"></a>
      </div>
      <div id="contentcen">
        <div id="conchild">
          <#list tbMap?keys as key>
            <div class="section">
              <h2 id="${key}">${key}</h2>
              <div class="content">
                <#list tbMap[key] as tb>
                  <div>
                    <h3 id="${tb.name}">${tb.fullName}</h3>
                    <table width="100%">
                      <tr bgcolor="#ddd" style="height:40px;">
                        <td width="15%">字段名称</td>
                        <td width="18%">数据类型</td>
                        <td width="10%">字段含义</td>
                        <td width="25%">功能说明</td>
                        <td width="">备注</td>
                      </tr>
                      <#list tb.paramList as param>
                        <tr style="height:40px;">
                          <td width="15%">${param.name}</td>
                          <td width="18%">${param.type}</td>
                          <td width="10%">${param.content}</td>
                          <td width="25%">${param.desc}</td>
                          <td width="">${param.remark}</td>
                        </tr>
                      </#list>
                    </table>
                  </div>
                  <br>
                </#list>
                <br><br>
              </div>
            </div>
          </#list>
        </div>
      </div>

    </div>
  </div>
</body>

</html>