<h3>
  Magic Profile - ${user.player.name }
  <@sec.authorize access="hasRole('ROLE_ADMIN')">
  <button id="btn-ban-user" class="btn btn-danger"><i class="fam-user-delete"></i> Ban user</button>
  </@sec.authorize>
</h3>