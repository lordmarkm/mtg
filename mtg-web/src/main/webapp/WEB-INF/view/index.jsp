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
  <#include "templates/imageupload.jsp">
    
  <div class="container">
    <div id="loadhere-container" class="span7" style="position: relative;">
      <div id="loadhere"></div>
    </div>
    <div id="rightpane" class="span4">
     <#include "templates/updates.jsp">
    </div>
  </div>
</body>

<script>
var page = {
		target : '${target?if_exists}',
		welcome: '<@spring.url "/support/welcome" />'
}
</script>

</html>