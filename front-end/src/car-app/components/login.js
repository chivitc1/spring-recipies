import React, { Component } from 'react';
import { API_URL } from './config';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import CarList from './car-list';
import Snackbar from '@material-ui/core/Snackbar';

export default class Login extends Component {
    constructor(props) {
        super(props);
        this.state = { username: '', password: '', isAuthenticated: false, statusBarOpen: false };
    }

    handleChange = (event) => {
        this.setState({ [event.target.name]: event.target.value });
    }

    handleStatusBarClose = (event) => {
        this.setState({ statusBarOpen: false });
    }

    login = () => {
        const user = { username: this.state.username, password: this.state.password };
        fetch(`${API_URL}/auth/signin`, {
            method: 'POST',
            body: JSON.stringify(user),
            headers: { 'Content-Type': 'application/json' }
        })
            .then(res => res.json())
            .then(responseData => {
                const jwtToken = responseData.token;
                if (jwtToken) {
                    sessionStorage.setItem("jwt", jwtToken);
                    this.setState({ isAuthenticated: true });
                } else {
                    this.setState({ statusBarOpen: true })
                }
            })
            .catch(err => console.log(err));
    }

    logout = () => {
        sessionStorage.removeItem("jwt");
        this.setState({ isAuthenticated: false });
    }

    render() {
        if (this.state.isAuthenticated === true) {
            return <CarList />
        } else {
            return (
                <div style={{ paddingTop: 150 }}>
                    <TextField name="username" placeholder="Username" onChange={this.handleChange} /><br />
                    <TextField name="password" placeholder="Password" type="password" onChange={this.handleChange} /><br />
                    <Button variant="contained" color="primary" onClick={this.login}>Login</Button>
                    <Snackbar open={this.state.statusBarOpen} onClose={this.handleStatusBarClose}
                        autoHideDuration={1500}
                        message='Invalid username or password' />
                </div>
            );
        }

    }
}

