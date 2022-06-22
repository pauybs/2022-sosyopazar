import Map, { Marker } from 'react-map-gl';

function MapBox({ latitude, longitude }) {
    return (
        <Map
            mapboxAccessToken="pk.eyJ1IjoibWNhdmxhayIsImEiOiJjbDM5b3g2eTAwYnVhM2pyMzEwd3ZlN3dpIn0.XDVEAmyp5LCGV_YMuLb7JA"
            initialViewState={{
                longitude: parseFloat(longitude),
                latitude: parseFloat(latitude),
                zoom: longitude && latitude ? 14 : 6
            }}
            style={{ width: "100%", height: 400 }}
            mapStyle="mapbox://styles/mapbox/streets-v9"
        >,
            <Marker longitude={longitude} latitude={latitude} anchor="bottom" >

            </Marker>
        </Map>
    );
}

export default MapBox;