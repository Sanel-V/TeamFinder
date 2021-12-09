import React from "react";

export class Register extends React.Component {

    constructor(props) {
        super(props);
    }

    trigger = (event) => {
        this.props.toggleActive(true);
        event.preventDefault();
    }

    render() {
        return <div className="base-container">
            <div className="header">Register</div>
            <div className="content">
                <div className="image">
                    
                </div>
                <div className="form">
                    <div className="form-group">
                        <label htmlFor="email">Email</label>
                        <input type="email" name="email" placeholder="Email"/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input type="password" name="password" placeholder="Password"/>
                    </div>
                </div>
                <div className="switch">Already have account? <div className="switchButton" onClick = {this.trigger.bind(this)}>Login here.</div></div>
            </div>
            <div className="footer">
                <button type="button" className="btn">Register</button>
            </div>        
        </div>
    }
}