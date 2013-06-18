<#if page?? && page.bundles?has_content>

<table class="table table-striped">
  <thead>
    <tr>
      <th>Card</th>
      <th>Count</th>
      <th></th>
    </tr>
  </thead>

  <tbody>
    <#list page.bundles as bundle>
    <tr bundle-id="${bundle.id }">
      <td>${bundle.card.name }</td>
      <td>${bundle.count }</td>
      <td nowrap="nowrap">
        <button class="btn btn-primary btn-mini bundle-increment" title="Increment">
          <i class="icon-plus-sign icon-white"></i>
        </button>
        <button class="btn btn-warning btn-mini bundle-decrement" title="Decrement">
          <i class="icon-minus-sign icon-white"></i>
        </button>
        <button class="btn btn-danger btn-mini bundle-delete" title="Delete">
          <i class="icon-remove icon-white"></i>
        </button>
      </td>
    </tr>
    </#list>
  </tbody>
</table>

<#else>
<div class="alert alert-info">There's nothing on this page</div>
</#if>