import React from "react";
import "./profile.scss"
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";
import { Routes, Route, Link, useParams } from "react-router-dom";
import App from "../../App";
import { Accounts } from "../accounts/accounts";

export function Profile() {

    let { id } = useParams();
    let baseUrl = "http://localhost:8080";
    let profile = {};
    var token = localStorage.getItem('access_token');
    let success = false;

    axios.defaults.headers.common['Authorization'] = "Bearer: " + token;
    axios.get(`${baseUrl}/account/get/${id}`)
        .then(response => {
            success = true;
            profile = { profile: response.data };
            console.log(response.data);
        })
        .catch(function (error) {
            // handle error

            console.log(error);
        })
        .then(function () {
        });

        return <AllData id={id} profile={profile} success={success}/>

        //console.log(props.match.params.id);




}

function Render(props){
  return(
    <div className="container">
      
        <div className="main-body">
        
              <nav aria-label="breadcrumb" className="main-breadcrumb">
                <ol className="breadcrumb">
                  <li className="breadcrumb-item"><Link to='/' > Home </Link></li>
                  <li className="breadcrumb-item"><a href="">User Profile</a></li>
                </ol>
              </nav>
              <h1>{props.id}</h1>
              <LoadData profile={props.profile} success={props.success}/>

        </div>
    </div>
    )
}

function LoadData(props){
  console.log("success: "+props.success);
  if (props.success)
    return <AllData profile={props.profile}/>;
  else
    return <NoData />;
}

function NoData(){
  return(
    <div className="col-md-12 mb-3">
    <div className="card">
      <div className="card-body">
        <div className="d-flex flex-column align-items-center text-center">
          <div className="mt-3">
            <h4>No data on this user</h4>
                <Link to='/dashboard' > To the homePage </Link>
          </div>
        </div>
      </div>
    </div>
  </div>
  );
}

function AllData(profile){
  
  return(
      <div className="row gutters-sm">
      <div className="col-md-6 mb-3">
        <div className="card">
          <div className="card-body">
            <div className="d-flex flex-column align-items-center text-center">
              <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" className="rounded-circle" width="150"></img>
              <div className="mt-3">
                <h4>{profile.firstName}</h4>
                <p className="text-secondary mb-1">Full Stack Developer</p>
                <p className="text-muted font-size-sm">Bay Area, San Francisco, CA</p>
                <button className="btn btn-primary">Follow</button>
                <button className="btn btn-outline-primary">Message</button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="col-md-6">
        <div className="card mb-3">
          <div className="card-body">
            <div className="row">
              <div className="col-sm-3">
                <h6 className="mb-0">Full Name</h6>
              </div>
              <div className="col-sm-9 text-secondary">
                Kenneth Valdez
              </div>
            </div>
            <hr></hr>
            <div className="row">
              <div className="col-sm-3">
                <h6 className="mb-0">Email</h6>
              </div>
              <div className="col-sm-9 text-secondary">
                fip@jukmuh.al
              </div>
            </div>
            <hr></hr>
            <div className="row">
              <div className="col-sm-3">
                <h6 className="mb-0">Phone</h6>
              </div>
              <div className="col-sm-9 text-secondary">
                (239) 816-9029
              </div>
            </div>
            <hr></hr>
            <div className="row">
              <div className="col-sm-3">
                <h6 className="mb-0">Address</h6>
              </div>
              <div className="col-sm-9 text-secondary">
                Bay Area, San Francisco, CA
              </div>
            </div>
            <hr></hr>
            <div className="row">
              <div className="col-sm-12">
                <Link className="btn btn-info " to='/edit' > Edit </Link>
              </div>
            </div>
          </div>
        </div>

      </div>

      <div className="col-md-12 mb-3">
        <div className="card">
          <div className="card-body">
            <div className="d-flex flex-column align-items-center text-center">
              <div className="my-2">
                  <div className="row">
                          <h6 className="mb-0 float-left">About me</h6>
                      <div style={{textAlign: "justify"}} className="text-secondary" >
                          Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptas quas vitae earum optio sint, inventore nulla nesciunt sunt nam dolores in laudantium maxime, amet, aspernatur eos illum! Nam, quam explicabo!
                          Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptas quas vitae earum optio sint, inventore nulla nesciunt sunt nam dolores in laudantium maxime, amet, aspernatur eos illum! Nam, quam explicabo!
                      </div>
                  </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div className="mb-3">
        <div className="card">
          <div className="card-body">
            <div className="d-flex flex-column align-items-center text-center">
              <div className="my-2">
                  <div className="row">
                          <h6 className="mb-0 float-left">Tags</h6>
                      <div className="text-secondary" >
                      </div>
                  </div>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>

    );
}