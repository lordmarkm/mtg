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
        <button class="btn btn-primary btn-mini bundle-operation" bundle-op="increment" title="Increment">
          <i class="icon-plus-sign icon-white"></i>
        </button>
        <button class="btn btn-warning btn-mini bundle-operation" bundle-op="decrement" title="Decrement">
          <i class="icon-minus-sign icon-white"></i>
        </button>
        <button class="btn btn-danger btn-mini bundle-operation" bundle-op="delete" title="Delete">
          <i class="icon-remove icon-white"></i>
        </button>
        <button class="btn btn-success btn-mini edit-note" title="${bundle.note! }">
          <i class="icon-edit icon-white"></i>
        </button>
      </td>
    </tr>
    </#list>
  </tbody>
</table>

<#else>
<div class="alert alert-info">There's nothing on this page</div>
</#if>