<#import "/spring.ftl" as spring />

<div class="alert alert-${type }">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  ${message }
</div>

<a href="<@spring.url '/account/dashboard' />">Back to account dashboard</a>