<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<body>
    <h2 style="text-align: center">${projectName} 数据库文档</h2>
    <#assign var = 1 >
    <#list tbMap?keys as key>
    <div style="margin: 10px 10px 30px 30px">
        <h3>${key}</h3>
        <#list tbMap[key] as tb>
        <div>
            <h4>表${var} ${tb.fullName}</h4>
            <#assign var = var + 1 >
            <table border="1" cellpadding="2" cellspacing="0" style="border-collapse:collapse;width: 95%;text-align: center">
                <tr style="height:45px;background-color:#EBEBEB">
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
        <br/>
        </#list>
    </div>
    </#list>
    <br/><br/>
</body>
</html>