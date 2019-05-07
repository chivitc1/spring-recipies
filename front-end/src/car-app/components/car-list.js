import React, { Component } from 'react'
import ReactTable from 'react-table';
import 'react-table/react-table.css';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css'
import AddCar from './add-car';
import { CSVLink} from 'react-csv';


const API_URL = `${process.env.REACT_APP_API_PROTOCOL}://${process.env.REACT_APP_API_HOST}:${process.env.REACT_APP_API_PORT}/api`;
const CARS_API_URL = `${API_URL}/cars`;

export class CarList extends Component {
    constructor(props) {
        super(props)

        this.state = {
            cars: []
        };
    }

    componentDidMount() {
        this.fetchCars();
    }

    fetchCars = () => {
        fetch(CARS_API_URL)
            .then(response => response.json())
            .then(responseData => {
                this.setState({
                    cars: responseData
                })
            })
            .catch(err => console.error(err));
    }

    addCar = (car) => {
        fetch(CARS_API_URL,
            {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(car)
            })
            .then(res => this.fetchCars())
            .catch(err => console.log(err))
    }

    updateCar = (car, id) => {
        fetch(`${CARS_API_URL}/${id}`,
            {
                method: 'PATCH',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(car)
                
            })
        .then(res => {
            if (res.status != 200) {
                throw new Error(res.json().error);
            }   
            this.fetchCars();
            toast.success("Changed saved", {
                position: toast.POSITION.BOTTOM_LEFT
            });
        })
        .catch(err => {
            toast.error("Error when saving", {
                position: toast.POSITION.BOTTOM_LEFT
            });
        })
    }

    onDelClick = (id) => {
        fetch(`${CARS_API_URL}/${id}`,
            { method: 'DELETE' })
            .then(res => {
                toast.success("Car deleted", { position: toast.POSITION.BOTTOM_LEFT });
                this.fetchCars();
            })
            .catch(err => {
                toast.error("Error when deleting", { position: toast.POSITION.BOTTOM_LEFT });
                console.log(err)
            });
    }

    confirmDelete = (id) => {
        confirmAlert({
            message: 'Are you sure to delete?',
            buttons: [
                { label: 'Yes', onClick: () => this.onDelClick(id) },
                { label: 'No' }
            ]
        })
    }

    renderEditable = (cellInfo) => {
        return (
            <div
                style={{ backgroundColor: '#fafafa' }}
                contentEditable
                suppressContentEditableWarning
                onBlur={
                    e => {
                        const data = [...this.state.cars];
                        data[cellInfo.index][cellInfo.column.id] = e.target.innerHTML;
                        this.setState({cars: data});
                    }
                }
                dangerouslySetInnerHTML={{
                    __html: this.state.cars[cellInfo.index][cellInfo.column.id]
                }}
            />
        )
    }
    render() {
        const columns = [{
            Header: 'Brand',
            accessor: 'brand',
            Cell: this.renderEditable
        }, {
            Header: 'Model',
            accessor: 'model',
            Cell: this.renderEditable
        }, {
            Header: 'Color',
            accessor: 'color',
            Cell: this.renderEditable
        }, {
            Header: 'Year',
            accessor: 'year',
            Cell: this.renderEditable
        }, {
            Header: 'Price',
            accessor: 'price',
            Cell: this.renderEditable
        }, {
            id: 'saveButton',
            sortable: false,
            filterable: false,
            width: 100,
            accessor: 'id',
            Cell: ({ value, row }) => (<button onClick={() => { this.updateCar(row, value) }}>Save</button>
            )
        }, {
            id: 'delButton',
            sortable: false,
            filterable: false,
            width: 100,
            accessor: 'id',
            Cell: ({ value }) => (<button onClick={() => { this.confirmDelete(value) }}>Delete</button>
            )
        }]

        return (
            <div className="App">
                <CSVLink data={this.state.cars} separator=";">Export CSV</CSVLink>
                <AddCar addCar={this.addCar} fetchCars={this.fetchCars} />
                <ReactTable data={this.state.cars} columns={columns} filterable={true} />
                <ToastContainer autoClose={6500} />
            </div>
        );
    }
}

export default CarList
