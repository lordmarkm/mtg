<#import "/spring.ftl" as spring />
<script src="<@spring.url '/javascript/account-wantlist.js' />"></script>

<h3>Want list</h3>
<#if player.wanted?has_content>
<table class="table table-striped">
  <thead>
    <tr>
      <th>Name</th>
      <th>Expansion</th>
      <th>Count</th>
      <th>Note</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
    <#list player.wanted as wanted>
    <tr wanted-id="${wanted.id?c }">
      <td><a href="<@spring.url '/cards/${wanted.card.id?c }' />" target="_blank">${wanted.card.name }</a></td>
      <td>${wanted.card.expansion.name }</td>
      <td class="wanted-count">${wanted.count }</td>
      <td class="wanted-note">${wanted.note! }</td>
      <td>
        <button class="btn btn-primary btn-mini wanted-operation" wanted-op="increment" title="Increment">
          <i class="icon-plus-sign icon-white"></i>
        </button>
        <button class="btn btn-warning btn-mini wanted-operation" wanted-op="decrement" title="Decrement">
          <i class="icon-minus-sign icon-white"></i>
        </button>
        <button class="btn btn-danger btn-mini wanted-operation" wanted-op="delete" title="Delete">
          <i class="icon-remove icon-white"></i>
        </button>
        <button class="btn btn-success btn-mini wanted-editnote" title="Edit note">
          <i class="icon-edit icon-white"></i>
        </button>
        <a href="<@spring.url '/cards/find/${wanted.card.id?c }' />" class="btn btn-mini" title="Find in binders">
          <i class="icon-search"></i>
        </a>
      </td>
    </tr>
    </#list>
  </tbody>
</table>
<#else>
<div class="alert alert-info">You are not looking for any cards</div>
</#if>