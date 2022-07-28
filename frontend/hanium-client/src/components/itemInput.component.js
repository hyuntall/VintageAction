import React, { Component } from 'react';
import Uploader from './Uploader';

export default class itemInput extends Component {

  render() {
    return (
      <form>
        <h3>Item Input</h3>
        <div className="mb-3">
          <label>Title</label>
          <input type="text" className="form-control" />
        </div>
        <div>
          <Uploader />
        </div>
        <div className="mb-3">
          <label>내용</label>
          <input type="textarea" className="form-control" />
        </div>
        <div className="mb-3">
          <label>가격</label>
          <input type="price" className="form-control" />
          원
        </div>
        <div className="d-grid">
          <button type="submit" className="btn btn-primary">
            등록
          </button>
        </div>
      </form>
    );
  }
}
