
import { AddPhotoAlternateRounded } from '@mui/icons-material';
import { Box, Button } from '@mui/material';
import ImageUploading from 'react-images-uploading';
import { addCoverPhoto } from '../api/controllers/seller-controller';

const SellerCoverPhoto = ({ photoUrl, fetchStore }) => {

    const onChange = async (imageList, addUpdateIndex) => {
        console.log(imageList, addUpdateIndex);

        let formData = new FormData();

        formData.append('file', imageList[0].file)

        await addCoverPhoto(formData);

        fetchStore();
    };

    return (

        <ImageUploading

            onChange={onChange}
            dataURLKey="data_url"
        >
            {({
                onImageUpload,
            }) => (
                <Box className='store-cover' sx={{ backgroundImage: `url(${photoUrl})`, position: "relative" }}>
                    <Button sx={{ color: "#fff", position: "absolute", top: "1rem", right: "1rem" }} startIcon={<AddPhotoAlternateRounded />} onClick={onImageUpload}>Kapağı Değiştir</Button>
                </Box >
            )
            }
        </ImageUploading >

    );
}

export default SellerCoverPhoto;