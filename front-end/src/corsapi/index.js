import React from 'react';
import ReactTable from 'react-table';
import 'react-table/react-table.css';

// const API_URL = 'http://localhost:8080/api/cars';
// const ACCESS_TOKEN = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbIkFETUlOIl0sImlhdCI6MTU1Njc3ODY0MywiZXhwIjoxNTU2NzgyMjQzfQ.y58hqqm1a8f_J0xP0SO-JIQi_GG7uUjHzl-r19ek2AY';
const API_URL = 'http://chinv-macmini.local:8081/api/v1/mails';
const ACCESS_TOKEN = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwic2NvcGVzIjp7InRlYW1Sb2xlcyI6W3sidGVhbUlkIjoxLCJyb2xlIjoiQURNSU4ifSx7InRlYW1JZCI6Miwicm9sZSI6Ik1FTUJFUiJ9XSwic3lzUm9sZXMiOltdfSwiaWF0IjoxNTU2NzcxMzQzLCJleHAiOjE1NTg5MTg4Mjd9.aefzuXoguYfUvFVSdrmUNEUQemtqCoZgjCLaJMWkRRO3iW0gQlpHaCF__vp_dkDuJTjfyCi33YTCkMEkBOpqMA';

class CorsDemo extends React.Component {
    constructor(props) {
        super(props);
        this.state = { data: [] };
    }

    componentDidMount() {
        this.fetchData();
    }

    fetchData = () => {
        fetch(API_URL, {
            method: 'GET',
            headers: {
                token: `${ACCESS_TOKEN}`
            }
        })
        .then(response => response.json())
        .then(result => {
            console.log(result.items);
            this.setState({
                data: result.items
            })
        })
        .catch(err => console.error(err));
    }

    render() {
        const columns = [{
            Header: 'ID',         // Header of column
            accessor: 'id'   // Value accessor
        }, {
            Header: 'Subject',
            accessor: 'subject'
        }]
        return (
            <div className="App">
            <button onClick={this.fetchData}>Fetch</button>
                <ReactTable data={this.state.data} columns={columns} 
                    filterable={true} defaultPageSize={10} />
            </div>
        );
    }
}

export default CorsDemo;
