<#import "/spring.ftl" as spring />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<h3>Account Details</h3>
<p>Username: <@sec.authentication property="principal.username" />
<p>Authorities: <@sec.authentication property="principal.authorities" />

<h3>Expansions</h3>