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
    
  <div class="container">
  <#if target??>
  <h1>Target uri: ${target }</h1>
  <#else>
  <h1>Welcome!</h1>
  </#if>
  
  <a href="<@spring.url '/auth/login' />">Login</a>
  <a href="<@spring.url '/support/faq' />">FAQ</a>

  <div id="loadhere">Load things here</div>
  
  </div>
</body>

<script>
var page = {
		target : '${target?if_exists}'
}
</script>

</html>