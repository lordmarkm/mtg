<h3>
  Magic Profile - <#if target??>${target.player.name }<#else>${user.player.name }</#if>
  <@sec.authorize access="hasRole('ROLE_ADMIN')">
  <button id="btn-ban-user" class="btn btn-danger"><i class="fam-user-delete"></i> Ban user</button>
  </@sec.authorize>
</h3>