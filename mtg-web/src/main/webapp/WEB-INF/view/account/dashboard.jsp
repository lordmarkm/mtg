<#import "/spring.ftl" as spring />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<h3>Account Details</h3>
<p>Username: <@sec.authentication property="principal.username" />
<p>Authorities: <@sec.authentication property="principal.authorities" />

<h3>Magic Profile</h3>
<p>Name: ${account.player.name}

<h3>Binders</h3>
<ol>
<#list account.player.binders as binder>
  <li><a href="<@spring.url '/u/${account.player.name }/${binder.urlFragment }' />">${binder.name }</a></li>
</#list>
</ol>
<a class="btn" href="/account/newbinder">Create a binder</a>