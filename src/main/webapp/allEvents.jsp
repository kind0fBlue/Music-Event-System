<%--
  Created by IntelliJ IDEA.
  User: chengyihuang
  Date: 20/8/2023
  Time: 3:50 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

{% block main_body %}
<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    Employee management
    <small>Library management system</small>
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
    <li class="active">Employee management</li>
  </ol>
</section>

<!-- Main content -->
<section class="content container-fluid">

  <div class="row">
    <div class="col-xs-12">
      <div class="box">
        <div class="box-header">
          <h3 class="box-title">Employee information</h3>

          <div class="box-tools">
            <form action="{% url 'myadmin_user_index' 1 %}" method="get">
              <div class="input-group input-group-sm" style="width:250px;">
                <input type="text" name="keyword" class="form-control pull-right" placeholder="Username/Nickname">

                <div class="input-group-btn">
                  <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                </div>
              </div>
            </form>
          </div>
        </div>
        <!-- /.box-header -->
        <div class="box-body table-responsive no-padding">
          <table class="table table-hover">
            <tr>
              <th>ID</th>
              <th>Username</th>
              <th>Nickname</th>
              <th>Status</th>
              <th>Add time</th>
              <th>Edit time</th>
              <th>Operation</th>
            </tr>
            {% for vo in userlist %}
            <tr>
              <td>{{ vo.id }}</td>
              <td>{{ vo.username }}</td>
              <td>{{ vo.nickname }}</td>
              <td>
                {% if vo.status == 1 %}
                <span style="color:green">Normal</span>
                {% elif vo.status == 2 %}
                <span style="color:red">Disabled</span>
                {% elif vo.status == 6 %}
                <span style="color:blue">Admin</span>
                {% elif vo.status == 9 %}
                <span style="color:red">Deleted</span>
                {% else %}
                <span style="color:red">Unknown</span>
                {% endif %}
              </td>
              <td>{{ vo.create_at|date:'Y-m-d H:i:s' }}</td>
              <td>{{ vo.update_at|date:'Y-m-d H:i:s' }}</td>
              <td>
                <a type="button" href="{% url 'myadmin_user_edit' vo.id %}" class="btn btn-success btn-xs">
                  <span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Edit</a>
                <a type="button" href="{% url 'myadmin_user_delete' vo.id %}" class="btn btn-danger btn-xs">
                  <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete</a>
              </td>
            </tr>
            {% endfor %}
          </table>
        </div>
        <!-- /.box-body -->
        <div class="box-footer clearfix">
          <a role="button" href="{% url 'myadmin_user_add' %}" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Add employee</a>
          <ul class="pagination pagination-sm no-margin pull-right">
            <li><a href="{% url 'myadmin_user_index' pIndex|add:-1 %}?{{mywhere|join:'&'}}">&laquo;</a></li>
            {% for p in plist %}
            <li {% if p == pIndex %}class="active"{% endif %}><a href="{% url 'myadmin_user_index' p %}?{{mywhere|join:'&'}}">{{p}}</a></li>
            {% endfor %}

            <li><a href="{% url 'myadmin_user_index' pIndex|add:1 %}?{{mywhere|join:'&'}}">&raquo;</a></li>
          </ul>
        </div>
      </div>
      <!-- /.box -->
    </div>
  </div>

</section>
<!-- /.content -->
{% endblock %}