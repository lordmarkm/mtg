<#import "/spring.ftl" as spring />
<#import "./templates/includes.ftl" as includes />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<!DOCTYPE html>
<html>

<head>
  <@includes.universals />
  <title>Magic Online Binder</title>
</head>

<body>
  <#include "templates/navbar.jsp">
  <#include "templates/footer.jsp">
    
  <div class="container">
    <div id="loadhere" class="span7"></div>
    <div id="rightpane" class="span4"></div>
  </div>
</body>

<script>
var page = {
		target : '${target?if_exists}'
}
</script>

</html>