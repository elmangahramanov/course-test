var clickedButton;

$(function () {
    $('#idButtonStudent').click(function () {
        getAllStudent();
    });

    $('#idButtonTeacher').click(function () {
        getAllTeacher();
    });

    $('#idButtonCourse').click(function () {
        getAllCourse();
    });

    $('#idButtonAdd').click(function () {
        switch (clickedButton) {
            case 'student':
                openNewStudentDialog();
                break;
            case 'teacher':
                openNewTeacherDialog();
                break;
            case 'course':
               openNewCourseDialog();
                break;
            default:
                alert("Error choice!");

        }
    });

});


function getAllStudent() {

    $.ajax({
        url: '/ss?action=getAllStudent',
        type: 'GET',
        dataType: 'html',
        success: function (data) {
            $('#idDivStudent').html(data);
            $('#idDivStudent').show();
            $('#idDivTeacher').hide();
            $('#idDivCourse').hide();

        }
    });
    clickedButton = 'student';

}

function getAllTeacher() {

    $.ajax({
        url: '/ts?action=getAllTeacher',
        type: 'POST',
        dataType: 'html',
        success: function (data) {
            $('#idDivTeacher').html(data);

            $('#idDivTeacher').show();
            $('#idDivStudent').hide();
            $('#idDivCourse').hide();
        }
    });

    clickedButton = 'teacher';
}

function getAllCourse() {

    $.ajax({
        url: '/cs?action=getAllCourse',
        type: 'POST',
        dataType: 'html',
        success: function (data) {
            $('#idDivCourse').html(data);

            $('#idDivCourse').show();
            $('#idDivStudent').hide();
            $('#idDivTeacher').hide();
        }
    });
    clickedButton = 'course';
}

function deleteStudent(id) {
    var condition = false;
    condition = confirm("Are you sure?");
    if (condition) {
        $.ajax({
            url: '/ss?action=deleteStudent',
            type: 'POST',
            data: 'id=' + id,
            success: function () {
                alert("Student deleted");
                getAllStudent();
            },
            error: function () {
                alert("Error");
            }

        })
    }
}

function deleteTeacher(id) {
    var condition = false;
    condition = confirm("Are you sure?");
    if (condition) {
        $.ajax({
            url: '/ts?action=deleteTeacher',
            type: 'POST',
            data: 'id=' + id,
            success: function () {
                alert("Teacher deleted");
                getAllTeacher();
            },
            error: function () {
                alert("Error");
            }

        })
    }
}

function deleteCourse(id) {
    var condition = false;
    condition = confirm("Are you sure?");
    if (condition) {
        $.ajax({
            url: '/cs?action=deleteCourse',
            type: 'POST',
            data: 'id=' + id,
            success: function () {
                alert("Course deleted");
                getAllCourse()
            },
            error: function () {
                alert("Error")
            }
        })
    }
}

function openNewStudentDialog() {
    $.ajax({
        url:'/ns?action=getNewStudentJsp',
        type:'GET',
        dataType:'html',
        success:function (data) {
            $('#idDialogNewData').html(data);
            $('#idDialogNewData').dialog('open');
        }
    })
}
function openNewTeacherDialog() {
    $.ajax({
        url:'/ns?action=getNewTeacherJsp',
        type:'GET',
        dataType:'html',
        success:function (data) {
            $('#idDialogNewData').html(data);
            $('#idDialogNewData').dialog('open');
        }
    })

}
function openNewCourseDialog() {
    $.ajax({
        url:'/ns?action=getNewCourseJsp',
        type:'GET',
        dataType:'html',
        success:function (data) {
            $('#idDialogNewData').html(data);
            $('#idDialogNewData').dialog('open');
        }
    })
}
function addNewData() {
    switch (clickedButton){
        case 'student':
            addNewStudent();
            break;
        case 'teacher':
            addNewTeacher();
            break;
        case 'course':
            addNewCourse();
            break;
    }

}

function addNewStudent() {
    var firstName = $('#idInputStudentName').val();
    var lastName = $('#idInputStudentSurName').val();
    var dob = $('#idInputStudentDob').val();
    var idTeacher = $('#idSelectTeacher').val();

    $.ajax({
        url:'/ss?action=addStudent',
        type:'POST',
        data:'firstName='+firstName+'&lastName='+lastName+'&dob='+dob+'&idTeacher='+idTeacher,
        success:function () {
            alert("Student added");
            $('#idDialogNewData').dialog('close');
            getAllStudent();
        },
        error:function () {
            alert("Error");
        }
    })
}

function addNewTeacher() {

    var firstName = $('#idInputTeacherName').val();
    var lastName = $('#idInputTeacherSurname').val();
    var idCourse = $('#idSelectCourse').val();

    $.ajax({
        url:'/ts?action=addTeacher',
        type:'POST',
        data:'firstName='+firstName+'&lastName='+lastName+'&idSelectCourse='+idCourse,
        success:function () {
            alert("Teacher added");
            $('#idDialogNewData').dialog('close');
            getAllTeacher();
        },
        error:function () {
            alert("Error");
        }
    })

}

function addNewCourse() {
    var courseName = $('#idInputCourseName').val();
    var description = $('#idInputCourseDesc').val();

    $.ajax({
        url:'/cs?action=addCourse',
        type:'POST',
        data:'courseName='+courseName+'&description='+description,
        success:function () {
            alert("Course added");
            $('#idDialogNewData').dialog('close');
            getAllCourse();
        },
        error:function () {
            alert("Error");
        }
    })


}

// open edit dialogs

function openEditStudentDialog(idStudent) {

    $.ajax({
        url:'/ns?action=getEditStudentJsp',
        type:'GET',
        data:'id='+idStudent,
        dataType:'html',
        success:function (data) {
            $('#idDialogEditData').html(data);
            $('#idDialogEditData').dialog('open');
        }
    })

}

function openEditTeacherDialog (idTeacher) {
    $.ajax({
        url: '/ns?action=getEditTeacherJsp',
        type: 'GET',
        data:'id='+idTeacher,
        dataType: 'html',
        success: function (data) {
            $('#idDialogEditData').html(data);
            $('#idDialogEditData').dialog('open');
        }
    });
}
function openEditCourseDialog (idCourse) {
    $.ajax({
        url:'/ns?action=getEditCourseJsp',
        type:'GET',
        data:'id='+idCourse,
        dataType:'html',
        success:function (data) {
            $('#idDialogEditData').html(data);
            $('#idDialogEditData').dialog('open');
        }
    })
}


// edit data

function editNewData() {
    switch (clickedButton){
        case 'student':
            editStudent();
            break;
        case 'teacher':
            editTeacher();
            break;
        case 'course':
            editCourse();
            break;
    }

}

function editStudent() {
    var idStudent = $('#idInputStudentId').val();
    var firstName = $('#idInputStudentName').val();
    var lastName = $('#idInputStudentSurName').val();
    var dob = $('#idInputStudentDob').val();
    var idTeacher = $('#idSelectTeacher').val();

    $.ajax({
        url:'/ss?action=editStudent',
        type:'POST',
        data:'idStudent='+idStudent+'&firstName='+firstName+'&lastName='+lastName+'&dob='+dob+'&idTeacher='+idTeacher,
        success:function (data) {
            alert("Student edited");
            $('#idDialogEditData').dialog('close');
            getAllStudent();

        },
        error:function () {
            alert("Error");
        }
    })

}

function editTeacher() {
    var idTeacher = $('#idInputTeacherId').val();
    var firstName = $('#idInputTeacherName').val();
    var lastName = $('#idInputTeacherSurname').val();
    var idCourse = $('#idSelectCourse').val();

    $.ajax({
        url:'/ts?action=editTeacher',
        type:'POST',
        data:'idTeacher='+idTeacher+'&firstName='+firstName+'&lastName='+lastName+'&idCourse='+idCourse,
        success:function (data) {
            alert("Teacher edited");
            $('#idDialogEditData').dialog('close');
            getAllTeacher();
        },
        error:function () {
            alert("Error");
        }
    })
}
function editCourse() {
    var idCourse = $('#idInputCourseId').val();
    var courseName = $('#idInputCourseName').val();
    var description = $('#idInputCourseDesc').val();

    $.ajax({
        url:'/cs?action=editCourse',
        type:'POST',
        data:'idCourse='+idCourse+'&courseName='+courseName+'&description='+description,
        success:function (data) {
            alert("Course edited");
            $('#idDialogEditData').dialog('close');
            getAllCourse();
        },
        error:function () {
            alert("Error");
        }
    })
}









