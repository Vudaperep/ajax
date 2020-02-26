var rootURL = "http://localhost:8080/pt11/cars";

var currentCar;
var carTrIdPrefix = 'car_tr_';


$(function() {
    getAllCars();

    $('#btnDelete').hide();

    $('#refresh').click(function (e) {
        clearCarInputs();
    });

    $('#btnSave').click(function () {
        if ($('#id').val() == '') {
            addCars();
        }
        else {
            updateCar();
        }
    });

    $('#btnDelete').click(function () {
        deleteCar();
    });
});

function onChangeColourSearch() {
    search($('#searchKey').val());
}

$('#carList').on('click', 'a', function () {
    findById($(this).data('identity'));
});

function search(searchKey) {
    if (searchKey === '')
        getAllCars();
    else
        findByColour(searchKey);
}

function clearCarInputs() {
    $('#btnDelete').hide();
    $('#car_inputs').find(':input').val('');
}

function getAllCars() {
    $.ajax({
        type: 'GET',
        url: rootURL,
        dataType: "json",
        success: renderList
    });
}

function findByColour(searchKey) {
    $.ajax({
        type: 'GET',
        url: rootURL + '/' + 'by-colour/' + searchKey,
        dataType: "json",
        success: renderList,
        error:function(error){
            Swal.fire({
                type: 'error',
                title: 'We dont have car with this colour',
                text: renderError(error)
            });
        }
    });
}

function findById(id) {
    $.ajax({
        type: 'GET',
        url: rootURL + '/' + id,
        dataType: "json",
        success: function (data) {
            $('#btnDelete').show();
            console.log('findById success: ' + data.model);
            currentCar = data;
            renderDetails(currentCar);
        }
    });
}

function addCars(error) {
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: rootURL,
        dataType: "json",
        data: formToJSON(),
        success: function (data) {
            renderList(data, false)
        },
        error: function(error){
            Swal.fire({
                type: 'error',
                title: 'Name must be more than 3 characters',
                text: renderError(error)
            });
        }
    });

}

function renderError(error) {
    return JSON.parse(error.responseText).error;
}

function updateCar() {
    var carId = $('#id').val();
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: rootURL + '/' + carId,
        dataType: "json",
        data: formToJSON(),
        success: function (car) {
            $('#' + carTrIdPrefix + car.id).replaceWith([
                $('<tr id=' + carTrIdPrefix + car.id + '/>').append([
                    $('<td/>').html('<a href="#" data-identity="' + car.id + '">' + car.model + '</a>'),
                    $('<td/>').html('<a href="#" data-identity="' + car.id + '">' + car.colour + '</a>')])
                ])
        }
    });
}

function deleteCar() {
    var carId = $('#id').val();
    $.ajax({
        type: 'DELETE',
        url: rootURL + '/' + carId,
        success: function () {
            $('#' + carTrIdPrefix + carId).remove();
            clearCarInputs();

        }
    });
}

function renderList(data, shouldBeDataReloaded) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    if (shouldBeDataReloaded) {
        $('#carList tr').remove();
    }
    $.each(list, function (index, car) {
        $('#carList').append([
            $('<tr id=' + carTrIdPrefix + car.id + '/>').append([
                $('<td/>').html('<a href="#" data-identity="' + car.id + '">' + car.model + '</a>'),
                $('<td/>').html('<a href="#" data-identity="' + car.id + '">' + car.colour + '</a>')
            ])
        ]);
    });
}

function renderDetails(car) {
    $('#id').val(car.id);
    $('#model').val(car.model);
    $('#colour').val(car.colour);
}

function formToJSON() {
    var id = $('id').val();
    return JSON.stringify({
        "id": id === "" ? null : id,
        "model": $('#model').val(),
        "colour": $('#colour').val()
    });
}
