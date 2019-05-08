import React, { Component } from 'react'
import ReactTable from 'react-table';
import 'react-table/react-table.css';
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css'
import AddCar from './add-car';
import { CSVLink } from 'react-csv';
import Button from '@material-ui/core/Button';
import Grid from '@material-ui/core/Grid';
import Snackbar from '@material-ui/core/Snackbar';
import {API_URL} from './config';
import { getToken } from './utils';

const CARS_API_URL = `${API_URL}/cars`;
export class CarList extends Component {
    constructor(props) {
        super(props)

        this.state = {
            cars: [], open: false, message: ''
        };
    }

    componentDidMount() {
        this.fetchCars();
    }

    fetchCars = () => {
        fetch(`${API_URL}/cars`, { headers: {'Authorization': `Bearer ${getToken()}`}})
            .then(response => response.json())
            .then(responseData => {
                responseData.length && this.setState({
                    cars: responseData
                })
            })
            .catch(err => console.error(err));
    }

    addCar = (car) => {
        fetch(`${API_URL}/cars`,
            {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${getToken()}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(car)
            })
            .then(res => this.fetchCars())
            .catch(err => console.log(err))
    }

    updateCar = (car, id) => {
        fetch(`${CARS_API_URL}/${id}`,
            {
                method: 'PATCH',
                headers: {
                    'Authorization': `Bearer ${getToken()}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(car)

            })
            .then(res => {
                if (res.status !== 200) {
                    throw new Error(res.json().error);
                }
                this.fetchCars();
                this.setState({open: true, message: "Changes saved"});
            })
            .catch(err => {
                this.setState({open: true, message:"Error when saving" });
            })
    }

    onDelClick = (id) => {
        fetch(`${CARS_API_URL}/${id}`,
            { 
                method: 'DELETE', 
                headers: {'Authorization': `Bearer ${getToken()}`}
            })
            .then(res => {
                let msg;
                if (res.status === 200)  {
                    msg ="Car deleted";
                } else if (res.status === 403)  {
                    msg = "You have no authorization to delete car";
                 } else {
                     msg = "Error occur while deleting car";
                 }
                this.setState({ open: true, message: msg });
                this.fetchCars();                
            })
            .catch(err => {
                this.setState({open: true, message: "Error when deleting"});
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
                        this.setState({ cars: data });
                    }
                }
                dangerouslySetInnerHTML={{
                    __html: this.state.cars[cellInfo.index][cellInfo.column.id]
                }}
            />
        )
    }

    handleStatusBarClose = (event, reason) => {
        this.setState({open: false});
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
            Cell: ({ value, row }) => (<Button size="small" variant="text" color="primary"
                onClick={() => { this.updateCar(row, value) }}>Save</Button>
            )
        }, {
            id: 'delButton',
            sortable: false,
            filterable: false,
            width: 100,
            accessor: 'id',
            Cell: ({ value }) => (<Button size="small" variant="text" color="secondary"
                onClick={() => { this.confirmDelete(value) }}>Delete</Button>
            )
        }]

        return (
            <div className="App">
                <Grid container>
                    <Grid item>
                        <AddCar addCar={this.addCar} fetchCars={this.fetchCars} />
                    </Grid>
                    <Grid item style={{padding: 20}}>
                        <CSVLink data={this.state.cars} separator=";">Export CSV</CSVLink>
                    </Grid>
                </Grid>
                <ReactTable data={this.state.cars} columns={columns} filterable={true} />
                <Snackbar 
                    anchorOrigin={{
                        vertical: 'bottom',
                        horizontal: 'right',
                      }}
                    style={{width: 300, color: 'green'}}
                    open={this.state.open} 
                    onClose={this.handleStatusBarClose}
                    autoHideDuration={1500} message={this.state.message} />
            </div>
        );
    }
}

export default CarList
