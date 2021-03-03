<#import "parts/common.ftlh" as c>
<#import "parts/pager.ftlh" as p>


<@p.pager url filter page/>
 <span><a href="/v1/public/characters/add">Add</a></span>
<@c.page>

    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/v1/public/characters" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by description">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <div class="card-columns">
        <#list page.content as character>
            <div class="card">
               <img src="/img/${character.image}" height="300", width="200">
               <div class="container">
                   <h4 ><b><a href="/v1/public/characters/${character.name}/comics">${character.name}</a></b></h4>
                   <h3><b>${character.tag}</b></h3>
                   <p class="card-text">${character.description}</p>
               </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@c.page>