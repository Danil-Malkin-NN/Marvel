<#import "parts/common.ftlh" as c>


<@c.page>

    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/v1/public/characters" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by tag">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <div>
        <form method="post" action="/v1/public/characters" enctype="multipart/form-data">
            <input type="text" name="name" placeholder="Введите имя" />
            <input type="text" name="tag" placeholder="Тэг">
            <input type="text" name="description" placeholder="Описание">
            <input type="file" name="file">
            <button type="submit">Добавить</button>
        </form>
    </div>

</@c.page>
