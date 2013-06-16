<#import "/spring.ftl" as spring />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<h3>Account Details</h3>
<dl>
  <dt>Username</dt>
  <dd><@sec.authentication property="principal.username" /></dd>
  <dt>Authorities</dt>
  <dd><@sec.authentication property="principal.authorities" /></dd>
</dl>

<h3>Magic Profile</h3>
<div class="row-fluid">
  <div class="span4">
    <ul class="thumbnails">
      <li>
        <div class="thumbnail">
          <div class="replacable-image-container" upload-url="<@spring.url '/account/upload/profilepic' />"
           upload-title="Change profile picture">
            <#if account.player.image??>
            <img alt="300x200" src="<@spring.url '/image/${account.player.image.id }' />" />
            <#else>
            <img alt="300x200" src="<@spring.url '/images/no-img.jpg' />" />
            </#if>
          </div>
          <div class="caption">
            <span class="muted">Click to change</span>
          </div>
        </div>
      </li>
    </ul>
  </div>
  <div class="span6">
    <dl>
      <dt>Name</dt>
      <dd>${account.player.name }</dd>
    </dl>
  </div>
</div>

<h3>Binders</h3>
<ol>
<#list account.player.binders as binder>
  <li><a href="<@spring.url '/u/${account.player.name }/${binder.urlFragment }' />">${binder.name }</a></li>
</#list>
</ol>
<a class="btn" href="/account/newbinder">Create a binder</a>