import React from 'react';
import ReactTable from 'react-table';
import 'react-table/react-table.css';

const GITHUB_REPO_URL = 'https://api.github.com/search/repositories?page=1&per_page=50';
class GithubRepoDemo extends React.Component {
    constructor(props) {
        super(props);
        this.state = { keyword: '', data: [] };
    }

    fetchData = () => {
        fetch(`${GITHUB_REPO_URL}&q=${this.state.keyword}`)
        .then(response => response.json())
        .then(result => {
            console.log(result);
            this.setState({
                data: result.items
            })
        })
        .catch(err => console.error(err));
    }

    handleChange = (e) => {
        this.setState({keyword: e.target.value});
    }

    render() {
        const columns = [{
            Header: 'Name',         // Header of column
            accessor: 'full_name'   // Value accessor
        }, {
            Header: 'URL',
            accessor: 'html_url'
        }, {
            Header: 'Owner',
            accessor: 'owner.login'
        }]
        return (
            <div className="App">
                <input type='text' onChange={this.handleChange} />
                <button onClick={this.fetchData} value={this.state.keyword}>Fetch</button>
                <ReactTable data={this.state.data} columns={columns} 
                    filterable={true} defaultPageSize={10} />
            </div>
        );
    }
}

export default GithubRepoDemo;
