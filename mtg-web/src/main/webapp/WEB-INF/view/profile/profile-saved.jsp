<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<#include "profile-header.jsp">
<@tools.profilenav player=user.player active=3/>