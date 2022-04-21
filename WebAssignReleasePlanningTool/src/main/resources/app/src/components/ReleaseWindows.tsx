import * as React from "react";
import { useState, useRef } from "react";
import { Modal } from 'react-magma-dom';
import moment from "moment";
const vis = require("./../../js/vis-timeline-graph2d.min.js");

import RawData from "./RawData";

interface Assignment {
  id: Number,
  group: Number,
  content: string,
  start: string,
  end: string,
  category: string,
  className: string,
  score?: string,
  daysAvailable?: Number,
  rosterCount?: Number,
  items?: Assignment[];
}

// Configuration for the Timeline
var options = {
  showTooltips: true,
  tooltip: {
    template: (item: Assignment, element: any, data: any) => {
      var template = "";
      if("items" in item) template += "<h5>" +item.items?.length + " Assignments clustered</h5>";
      else { 
        const startDate = moment(item.start).format("MM-DD-YYYY HH:mm:ss");
        const endDate = moment(item.end).format("MM-DD-YYYY HH:mm:ss");
        template += "<h5>" + item.content + "</h5><p><b>Start</b>: " 
        + startDate + "</p><p><b>End</b>: " 
        + endDate + "</p>";

        if(item.className === "item-assignment" || item.className === "item-exam") {
          template += "<p><b>Category</b>: "
          + item.category + "<p><b>Days Available</b>: "
          + item.daysAvailable + "</p><p><b>Roster Count</b>: " 
          + item.rosterCount + "</p>";
        }

      }
      return template;
    }
  },
  cluster: {
    maxItems: 5,
    clusterCriteria: function clusterCriteria(firstItem: Assignment, secondItem: Assignment) : boolean {
      if(firstItem.className === "item-assignment" && secondItem.className === "item-assignment") {
        return true;
      }

      return false;
    }
  },
  orientation: {
    axis: "both"
  }
};
var groups = [{
  id: 1,
  content: "Release Windows"
},{
  id: 2,
  content: "Assignments"
}];

export default function ReleaseWindows(props:{items: any[], startDate: Date, endDate: Date}) {
  const [timeline, setTimeline] = useState(null as any);
  const [showModal, setShowModal] = React.useState(false);
  const modalItems = useRef([]);
  const cols = useRef([
    { "field": "content", "header": "Assignment" },
    { "field": "category", "header": "Category" },
    { "field": "start", "header": "Start" },
    { "field": "end", "header": "End" },
    { "field": "daysAvailable", "header": "Days Available" },
    { "field": "rosterCount", "header": "Roster Count" }
  ]);
  var items = useRef(new vis.DataSet([]));

  const onClick = (props: any) => {
    if(props.item && typeof(props.item) === 'string') {
      console.log(props.event.target["vis-item"].data.items);
      modalItems.current = props.event.target["vis-item"].data.items.map(function(item: any) {
        return {
            id: item.id,
            content: item.content.toString(),
            category: item.category.toString(),
            start: moment(item.start.toString()).format("MM-DD-YYYY HH:mm:ss"),
            end: moment(item.end.toString()).format("MM-DD-YYYY HH:mm:ss"),
            daysAvailable: item.daysAvailable.toString(),
            rosterCount: item.rosterCount.toString(),
        };
      });
      setShowModal(true);
    }
  }

  React.useEffect(() => {
    var container: HTMLElement | null = document.getElementById('timeline');
    if(container != null) {
      container.innerHTML = '';
      items.current.clear();
      items.current.add(props.items);

      let newTimeline = new vis.Timeline(container, items.current, groups, options);
      newTimeline.on('click', onClick);
      setTimeline(newTimeline);
    }
  }, [props.items]);

  return (<>
      <Modal
        onClose={() => setShowModal(false)}
        isOpen={showModal}
      >
      <RawData columns={cols.current} rows={modalItems.current} export={false}/>
      </Modal>
      <div id="timeline"></div></>);
}
